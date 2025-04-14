package android.widget;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SwipeMenuView extends LinearLayout implements View.OnClickListener {
    private SwipeMenuLayout mLayout;
    private SwipeMenuListView mListView;
    private SwipeMenu mMenu;
    private OnSwipeItemClickListener onItemClickListener;
    private int position;

    public interface OnSwipeItemClickListener {
        void onItemClick(SwipeMenuView swipeMenuView, SwipeMenu swipeMenu, int i);
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        int i2 = i;
        this.position = i2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SwipeMenuView(android.widget.SwipeMenu r11, android.widget.SwipeMenuListView r12) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r7 = r0
            r8 = r1
            android.content.Context r8 = r8.getContext()
            r7.<init>(r8)
            r7 = r0
            r8 = r2
            r7.mListView = r8
            r7 = r0
            r8 = r1
            r7.mMenu = r8
            r7 = r1
            java.util.List r7 = r7.getMenuItems()
            r3 = r7
            r7 = 0
            r4 = r7
            r7 = r3
            java.util.Iterator r7 = r7.iterator()
            r5 = r7
        L_0x0022:
            r7 = r5
            boolean r7 = r7.hasNext()
            if (r7 == 0) goto L_0x003a
            r7 = r5
            java.lang.Object r7 = r7.next()
            android.widget.SwipeMenuItem r7 = (android.widget.SwipeMenuItem) r7
            r6 = r7
            r7 = r0
            r8 = r6
            r9 = r4
            int r4 = r4 + 1
            r7.addItem(r8, r9)
            goto L_0x0022
        L_0x003a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.SwipeMenuView.<init>(android.widget.SwipeMenu, android.widget.SwipeMenuListView):void");
    }

    private void addItem(SwipeMenuItem swipeMenuItem, int i) {
        ViewGroup.LayoutParams layoutParams;
        LinearLayout linearLayout;
        SwipeMenuItem swipeMenuItem2 = swipeMenuItem;
        new LinearLayout.LayoutParams(swipeMenuItem2.getWidth(), -1);
        new LinearLayout(getContext());
        LinearLayout linearLayout2 = linearLayout;
        linearLayout2.setId(i);
        linearLayout2.setGravity(17);
        linearLayout2.setOrientation(1);
        linearLayout2.setLayoutParams(layoutParams);
        linearLayout2.setBackgroundDrawable(swipeMenuItem2.getBackground());
        linearLayout2.setOnClickListener(this);
        addView(linearLayout2);
        if (swipeMenuItem2.getIcon() != null) {
            linearLayout2.addView(createIcon(swipeMenuItem2));
        }
        if (!TextUtils.isEmpty(swipeMenuItem2.getTitle())) {
            linearLayout2.addView(createTitle(swipeMenuItem2));
        }
    }

    private ImageView createIcon(SwipeMenuItem swipeMenuItem) {
        ImageView imageView;
        new ImageView(getContext());
        ImageView imageView2 = imageView;
        imageView2.setImageDrawable(swipeMenuItem.getIcon());
        return imageView2;
    }

    private TextView createTitle(SwipeMenuItem swipeMenuItem) {
        TextView textView;
        SwipeMenuItem swipeMenuItem2 = swipeMenuItem;
        new TextView(getContext());
        TextView textView2 = textView;
        textView2.setText(swipeMenuItem2.getTitle());
        textView2.setGravity(17);
        textView2.setTextSize((float) swipeMenuItem2.getTitleSize());
        textView2.setTextColor(swipeMenuItem2.getTitleColor());
        return textView2;
    }

    public void onClick(View view) {
        View view2 = view;
        if (this.onItemClickListener != null && this.mLayout.isOpen()) {
            this.onItemClickListener.onItemClick(this, this.mMenu, view2.getId());
        }
    }

    public OnSwipeItemClickListener getOnSwipeItemClickListener() {
        return this.onItemClickListener;
    }

    public void setOnSwipeItemClickListener(OnSwipeItemClickListener onSwipeItemClickListener) {
        OnSwipeItemClickListener onSwipeItemClickListener2 = onSwipeItemClickListener;
        this.onItemClickListener = onSwipeItemClickListener2;
    }

    public void setLayout(SwipeMenuLayout swipeMenuLayout) {
        SwipeMenuLayout swipeMenuLayout2 = swipeMenuLayout;
        this.mLayout = swipeMenuLayout2;
    }
}
