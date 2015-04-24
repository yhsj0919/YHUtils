package xyz.yhsj.yhutils.tools.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.widget.ImageView;

public class ImageUtils {
	@SuppressWarnings("deprecation")
	public static void setImageBitmap(Context context, ImageView imageView,
			Bitmap bitmap) {
		// Use TransitionDrawable to fade in.
		final TransitionDrawable td = new TransitionDrawable(new Drawable[] {
				new ColorDrawable(android.R.color.transparent),
				new BitmapDrawable(context.getResources(), bitmap) });
		// noinspection deprecation
		imageView.setBackgroundDrawable(imageView.getDrawable());
		imageView.setImageDrawable(td);
		td.startTransition(200);
	}
}
