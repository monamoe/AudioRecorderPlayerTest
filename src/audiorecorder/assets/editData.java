package audiorecorder.assets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.Property;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author monamoe Editing metadata using tika-app-1.24.1.jar
 *
 */
public class editData {

    public static void main(String[] arg) throws FileNotFoundException, IOException, SAXException, TikaException {
        String filename = "test.wav";

        try {
            //open file;
            InputStream file = new FileInputStream(new File(filename));

            ContentHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();

            Parser parser = new Mp3Parser();

            ParseContext parseCtx = new ParseContext();

            parser.parse(file, handler, metadata, parseCtx);

            file.close();

//            metadata.set(Artist, "Bob");
            metadata.set(Property.get("title"), "bob");

            System.out.println("Title: " + metadata.get("title"));
            System.out.println("Aritsts: " + metadata.get("xmpDM:artist"));
            System.out.println("Compser: " + metadata.get("xmpDM:compser"));
            System.out.println("Genre: " + metadata.get("xmpDM:genre"));
            System.out.println("Album: " + metadata.get("xmpDM:album"));
            System.out.println("Album: " + metadata.get("xmpDM:album"));

            String[] names = metadata.names();

            System.out.println("ALL THE NAMES");
            for (int i = 0; i < names.length; i++) {
                System.out.println(names[i]);
            }

            System.out.println("");
            System.out.println("");
            System.out.println("");

            System.out.println(metadata.toString());

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //MP3FILE maven shit
//    Mp3File mp3file = new Mp3File("SomeMp3File.mp3");
//ID3v1 id3v1Tag;
//if (mp3file.hasId3v1Tag()) {
//  id3v1Tag =  mp3file.getId3v1Tag();
//} else {
//  // mp3 does not have an ID3v1 tag, let's create one..
//  id3v1Tag = new ID3v1Tag();
//  mp3file.setId3v1Tag(id3v1Tag);
//}
//id3v1Tag.setTrack("5");
//id3v1Tag.setArtist("An Artist");
//id3v1Tag.setTitle("The Title");
//id3v1Tag.setAlbum("The Album");
//id3v1Tag.setYear("2001");
//id3v1Tag.setGenre(12);
//id3v1Tag.setComment("Some comment");
//mp3file.save("MyMp3File.mp3");
}
