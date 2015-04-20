package dk.statsbiblioteket.cdrip.robot;


import com.google.common.collect.ImmutableList;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

public class BasicFunctionalityTest {


    @Test
    public void jimfsTest() throws IOException {
        // sample example
        FileSystem fs = Jimfs.newFileSystem(Configuration.unix());
        Path foo = fs.getPath("/foo");
        Files.createDirectories(foo);


        assertFileSystemContents(fs, 3, 0); // also has / and /work

        Path hello = foo.resolve("hello.txt");
        Files.write(hello, ImmutableList.of("hello world"), StandardCharsets.UTF_8);

        Assert.assertEquals("hello world", Files.readAllLines(hello).get(0));

        assertFileSystemContents(fs, 3, 1);

        fs.close();
    }

    private void assertFileSystemContents(FileSystem fs, long dirs, long files) throws IOException {
        long[] count = new long[2];
        Files.find(fs.getPath("/"), 100, (a, b) -> true).forEach(path -> count[Files.isDirectory(path) ? 1 : 0]++);
        Assert.assertEquals("dirs", dirs, count[1]);
        Assert.assertEquals("files", files, count[0]);
    }
}
