package net.ichigotake.android.yancha.app.chat;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

import net.ichigotake.android.common.os.RestoredBundle;
import net.ichigotake.android.yancha.app.R;

import org.jetbrains.annotations.NotNull;

public final class ChatFragment extends Fragment {

    private static final String KEY_POSITION = "position";
    private ListView messagesView;

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, parent, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.chat_message, menu);
        /*
        MenuItem menuItem = menu.findItem(R.id.action_settings);
        menuItem.setActionProvider(
                new IntentTripActionProvider(getActivity(), SettingActivity.createIntent(getActivity()))
        );
        */
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        @NotNull View view = getView();
        messagesView = (ListView) view.findViewById(R.id.fragmet_chat_message_list);
        final ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(
                getActivity(),
                R.layout.chat_message_item
        ) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.chat_message_item, parent, false);
                }
                return convertView;
            }
        };
        SwingBottomInAnimationAdapter swingAdapter = new SwingBottomInAnimationAdapter(adapter);
        swingAdapter.setAbsListView(messagesView);
        messagesView.setAdapter(swingAdapter);
        final Handler handler = new Handler();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.add(new Object());
                        }
                    });
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        int presetPosition = new RestoredBundle(this, savedInstanceState).getInteger(KEY_POSITION);
        messagesView.setSelection(presetPosition);

        messagesView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        messagesView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                //messagesView.setItemChecked(i, true);
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_POSITION, messagesView.getFirstVisiblePosition());
    }

}
