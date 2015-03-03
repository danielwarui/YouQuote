import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

public class TextFileReader {

	Context g;

	public TextFileReader(Context c) {
		this.g = c;

	}

	public String readTxt(String a) throws IOException {

		// InputStream inputStream =
		// g.getResources().openRawResource(R.raw.hym1);
		InputStream inputStream = g.getResources().getAssets().open("raw/" + a);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		int i;
		try {
			i = inputStream.read();
			while (i != -1) {
				byteArrayOutputStream.write(i);
				i = inputStream.read();
			}
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return byteArrayOutputStream.toString();
	}

}
