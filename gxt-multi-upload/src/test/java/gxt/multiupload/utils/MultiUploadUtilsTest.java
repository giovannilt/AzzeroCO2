package gxt.multiupload.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class MultiUploadUtilsTest {

	@Test
	public void testRemoveFilePathWindows() {
		String filepath = "file:\\c:\\fakepath\\file.txt";
		String filename = MultiUploadUtils.removeFilePath(filepath);
		assertEquals("file.txt", filename);
	}
	
	@Test
	public void testRemoveFilePathUnixBased() {
		String filepath = "file:/home/user/file.txt";
		String filename = MultiUploadUtils.removeFilePath(filepath);
		assertEquals("file.txt", filename);
	}
	
	@Test
	public void testRemoveFilePathWithNoPath() {
		String filepath = "file.txt";
		String filename = MultiUploadUtils.removeFilePath(filepath);
		assertEquals("file.txt", filename);
	}
}
