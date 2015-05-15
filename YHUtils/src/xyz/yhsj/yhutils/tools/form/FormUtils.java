package xyz.yhsj.yhutils.tools.form;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.inputmethodservice.ExtractEditText;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

/**
 * 表单工具类,获得数据或者设置数据
 * 
 * @author 修改 by 永恒瞬间（power by zftlive）
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public class FormUtils {

	/**
	 * 获取表单的数据（根据Tag获取控件：键-值，返回json格式）
	 * 
	 * @param root
	 *            当前表单容器
	 * @param data
	 *            当前表单数据
	 * @return 表单数据（CheckBox多选选项JSONArray拼接）
	 */

	@SuppressLint("NewApi")
	public static JSONObject getFormInfo(ViewGroup root, JSONObject data)
			throws Exception {
		if (root.getChildCount() > 0) {
			for (int i = 0; i < root.getChildCount(); i++) {

				View view = root.getChildAt(i);
				// 容器级别控件需要进行递归
				if (view instanceof LinearLayout) {
					getFormInfo((LinearLayout) view, data);
				} else if (view instanceof RelativeLayout) {
					getFormInfo((RelativeLayout) view, data);
				} else if (view instanceof FrameLayout) {
					getFormInfo((FrameLayout) view, data);
				} else if (view instanceof AbsoluteLayout) {
					getFormInfo((AbsoluteLayout) view, data);
				} else if (view instanceof RadioGroup) {
					getFormInfo((RadioGroup) view, data);
				} else if (view instanceof TableLayout) {
					getFormInfo((TableLayout) view, data);
				}

				// 非容器级别控件不用递归
				/**
				 * EditText.class
				 */
				else if (view instanceof EditText) {

					if (view.getTag() != null) {
						data.put((String) view.getTag(), ((EditText) view)
								.getText().toString());
					}

				} else if (view instanceof AutoCompleteTextView) {

					if (view.getTag() != null) {
						data.put((String) view.getTag(),
								((AutoCompleteTextView) view).getText()
										.toString());
					}

				} else if (view instanceof MultiAutoCompleteTextView) {

					if (view.getTag() != null) {
						data.put((String) view.getTag(),
								((MultiAutoCompleteTextView) view).getText()
										.toString());
					}

				} else if (view instanceof ExtractEditText) {

					if (view.getTag() != null) {
						data.put((String) view.getTag(),
								((ExtractEditText) view).getText().toString());
					}
				}

				/**
				 * RadioButton.class
				 */
				else if (view.getClass().getName()
						.equals(RadioButton.class.getName())) {

					if (view.getTag() != null) {
						if (((RadioButton) view).isChecked()) {
							data.put((String) view.getTag(),
									((RadioButton) view).getText().toString());
						}
					}
				}

				/**
				 * CheckBox.class(需要拼装选中复选框)
				 */
				else if (view.getClass().getName()
						.equals(CheckBox.class.getName())) {

					if (view.getTag() != null) {

						if (((CheckBox) view).isChecked()) {

							if (!data.isNull((String) view.getTag())) {

								JSONArray value = data.getJSONArray(view
										.getTag().toString());
								value.put(((CheckBox) view).getText()
										.toString());
								data.put((String) view.getTag(), value);
							} else {
								JSONArray arr = new JSONArray();
								arr.put(((CheckBox) view).getText().toString());
								data.put((String) view.getTag(), arr);

							}
						}
					}

				}
				/**
				 * Spinner.class
				 */
				else if (view.getClass().getName()
						.equals(android.widget.Spinner.class.getName())) {

					if (view.getTag() != null) {
						data.put((String) view.getTag(),
								((android.widget.Spinner) view)
										.getSelectedItem().toString());
					}
				}
			}
		}

		return data;
	}

	

}
