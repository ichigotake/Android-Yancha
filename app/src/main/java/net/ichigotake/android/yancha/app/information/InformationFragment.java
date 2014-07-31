package net.ichigotake.android.yancha.app.information;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import net.ichigotake.android.common.app.BaseDialogFragment;
import net.ichigotake.android.yancha.app.BuildConfig;
import net.ichigotake.android.yancha.app.R;

public final class InformationFragment extends BaseDialogFragment {

    private static final String FRAGMENT_TAG = "information_fragment";

    public static void open(FragmentManager fragmentManager) {
        Fragment fragment = new InformationFragment();
        fragmentManager.beginTransaction()
                .add(fragment, FRAGMENT_TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_dialog_information, null);
        TextView applicationVersionView = (TextView) view.findViewById(R.id.fragment_dialog_information_application_version);
        applicationVersionView.setText(
                "Yancha for Android v" + TextUtils.split(BuildConfig.VERSION_NAME, "-")[0]
        );
        TextView futureFeatureView = (TextView) view.findViewById(R.id.fragment_dialog_information_future_feature);
        String[] futureFeatures = {
                "通知",
                "過去ログ検索",
                "画像投稿",
                "ログインユーザー一覧の閲覧",
                "購読タグの管理",
                "省エネモード",
                "未読管理",
                "発言音",
                "チャット画面のページング",
                "yanchaの基本説明",
        };
        futureFeatureView.setText(TextUtils.join("\n", futureFeatures));
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setNegativeButton(R.string.dialog_close, null)
                .create();
    }
}
