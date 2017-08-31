package com.zhongdihang.bankdispatch.common.util;

import com.zhongdihang.bankdispatch.common.exceptions.BusinessException;
import com.zhongdihang.bankdispatch.rest.ResultStatus;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtil {
	
	private static Logger log = Logger.getLogger(FileUtil.class);
	
	/**
	 * NIO way
	 */
	public static byte[] toByteArray(String filename) {

		File f = new File(filename);
		if (!f.exists()) {
			log.error("文件未找到！" + filename);
			throw new BusinessException(ResultStatus.FAILURE,"文件未找到！");
		}
		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				// do nothing
				// System.out.println("reading");
			}
			return byteBuffer.array();
		} catch (IOException e) {
			throw new BusinessException(ResultStatus.FAILURE,"文件读取异常！");
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				throw new BusinessException(ResultStatus.FAILURE,"文件读取异常！");
			}
			try {
				fs.close();
			} catch (IOException e) {
				throw new BusinessException(ResultStatus.FAILURE,"文件读取异常！");
			}
		}
	}
}