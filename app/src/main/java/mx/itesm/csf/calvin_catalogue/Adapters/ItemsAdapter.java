package mx.itesm.csf.calvin_catalogue.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import mx.itesm.csf.calvin_catalogue.R;

/**
 * Created by rodo on 12/05/2018.
 */

public class ItemsAdapter extends BaseAdapter {

    private Context context;

    private Integer[] imageIds = {
            R.drawable.c_modelo, R.drawable.c_color,
            R.drawable.c_genero, R.drawable.c_edad
    };

    public ItemsAdapter(Context c) {
            context = c;
        }

    public int getCount() {
            return imageIds.length;
        }

    public Object getItem(int position) {
            return imageIds[position];
        }

    public long getItemId(int position) {
            return 0;
        }

    public View getView(int position, View view, ViewGroup parent)
    {
        ImageView iview;

        if (view == null)
        {
            iview = new ImageView(context);
            iview.setLayoutParams(new GridView.LayoutParams(400,400));
            iview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iview.setPadding(10, 20, 10, 10);
        }
        else
            iview = (ImageView) view;


        iview.setImageResource(imageIds[position]);
        return iview;
    }

    }
