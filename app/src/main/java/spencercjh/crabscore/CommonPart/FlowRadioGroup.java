package spencercjh.crabscore.CommonPart;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

/**
 * @项目名称:FlowRadioGroup
 * @包名：com.sunofbeaches.flowradiogroup.view
 * @作者：随便你咯
 * @类名：FlowRadioGroup
 * @创建时间：2015/12/180:29
 * @描述：自定义的radiogroup流式布局
 **/
public class FlowRadioGroup extends RadioGroup {


    public FlowRadioGroup(Context context) {
        this(context, null);
    }

    public FlowRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 复写测量的方法
     *
     * @param widthMeasureSpec  宽度
     * @param heightMeasureSpec 高度
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取到孩子的个数
        int childCount = getChildCount();
        int x = 0;
        int y = 0;
        int row = 0;

        //获取到最大宽度
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);


        //遍历孩子，并对它们进行测量
        for (int i = 0; i < childCount; i++) {
            //获取到孩子
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {

                child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
                int width = child.getMeasuredWidth();
                int height = child.getMeasuredHeight(); //计算出大小
                y = height * (row + 1);
                x += width;

                if (x > maxWidth) {
                    row++;
                    //如果大于了最大的宽度，那么就换行咯
                    x = width;
                    y = height * (row + 1);
                }
            }
            setMeasuredDimension(maxWidth, y);
            Log.d("message", "y = " + y);
        }
    }

    /**
     * 复写布局方法
     *
     * @param changed 是否改变
     * @param l       左
     * @param t       上
     * @param r       右
     * @param b       下
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //获取到孩子的个数
        int childCount = getChildCount();
        int x = 0;
        int y = 0;
        int row = 0;

        //计算最大宽度
        int maxWidth = r - l;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                int width = child.getMeasuredWidth();
                int height = child.getMeasuredHeight();

                //计算XY
                x += width;
                y = (row + 1) * height;
                if (x > maxWidth) {
                    //换行
                    row++;
                    x = width;
                    y = (row + 1) * height;
                }

                //设置孩子的位置
                child.layout(x - width, y - height, x, y);
            }
        }

    }
}