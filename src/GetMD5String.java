import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GetMD5String {
	private File file;
	private String filePath;

	public GetMD5String(File file) {
		this.file = file;
	}

	public GetMD5String(String filePath) {
		this.filePath = filePath;
	}

	public GetMD5String() {
	}

	public String getMD5OfFile() throws NoSuchAlgorithmException, IOException {
		if (this.file.isFile()) {
			FileInputStream fis = new FileInputStream(this.file);
			String strMD5 = getMD5string(fis);
			return strMD5;
		} else if (this.filePath.length() > 0) {
			FileInputStream fis = new FileInputStream(this.filePath);
			String strMD5 = getMD5string(fis);
			return strMD5;
		} else
			return null;
	}

	public String getMD5OfFile(String filePath)
			throws NoSuchAlgorithmException, IOException {
		FileInputStream fis = new FileInputStream(filePath);
		String strMD5 = getMD5string(fis);
		return strMD5;
	}

	public String getMD5OfFile(File file) throws NoSuchAlgorithmException,
			IOException {
		FileInputStream fis = new FileInputStream(file);
		String strMD5 = getMD5string(fis);
		return strMD5;
	}

	private String getMD5string(FileInputStream fis)
			throws NoSuchAlgorithmException, IOException {
		MessageDigest md;
		String strMD5 = null;

		md = MessageDigest.getInstance("MD5");
		byte[] dataBytes = new byte[1024];
		int nread = 0;
		while ((nread = fis.read(dataBytes)) != -1) {
			md.update(dataBytes, 0, nread);
		}
		byte[] mdbytes = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mdbytes.length; i++) {
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		strMD5 = sb.toString();

		return strMD5;
	}

}
