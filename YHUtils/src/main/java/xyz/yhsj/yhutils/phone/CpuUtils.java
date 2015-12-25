package xyz.yhsj.yhutils.phone;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by clf on 15/9/22.
 */
public class CpuUtils {
	/**
	 * 获取CPU最大频率（单位KHZ）
	 * 
	 * @return
	 */
	public static String getMaxCpuFreq() {

		String result = "";

		ProcessBuilder cmd;

		try {

			String[] args = { "/system/bin/cat",

			"/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq" };

			cmd = new ProcessBuilder(args);

			Process process = cmd.start();

			InputStream in = process.getInputStream();

			byte[] re = new byte[24];

			while (in.read(re) != -1) {

				result = result + new String(re);

			}

			in.close();

		} catch (IOException ex) {

			ex.printStackTrace();

			result = "N/A";

		}

		return result.trim();

	}

	/**
	 * 获取CPU最小频率（单位KHZ）
	 * 
	 * @return
	 */
	public static String getMinCpuFreq() {

		String result = "";

		ProcessBuilder cmd;

		try {

			String[] args = { "/system/bin/cat",

			"/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq" };

			cmd = new ProcessBuilder(args);

			Process process = cmd.start();

			InputStream in = process.getInputStream();

			byte[] re = new byte[24];

			while (in.read(re) != -1) {

				result = result + new String(re);

			}

			in.close();

		} catch (IOException ex) {

			ex.printStackTrace();

			result = "N/A";

		}

		return result.trim();

	}

	/**
	 * 实时获取CPU当前频率（单位KHZ）
	 * 
	 * @return
	 */
	public static String getCurCpuFreq() {

		String result = "N/A";

		try {

			FileReader fr = new FileReader(

			"/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");

			BufferedReader br = new BufferedReader(fr);

			String text = br.readLine();

			result = text.trim();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return result;

	}

	/**
	 * 获取CPU名字
	 * 
	 * @return
	 */
	public static String getCpuName() {

		try {

			FileReader fr = new FileReader("/proc/cpuinfo");

			BufferedReader br = new BufferedReader(fr);

			String text = br.readLine();

			String[] array = text.split(":\\s+", 2);

			for (int i = 0; i < array.length; i++) {

			}

			return array[1];

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return null;

	}
}
