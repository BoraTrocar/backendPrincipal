package br.edu.fateccotia.boratroca.utilits;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.util.StdConverter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class imgConvert extends StdConverter<File, byte[]> {
    @Override
    public byte[] convert(File file) {
        try {
            MultipartFile img = (MultipartFile) file;
            return img.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
