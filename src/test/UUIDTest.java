package test;

import java.util.UUID;

public class UUIDTest {
    public static void main(String[] args) {
        String id=UUID.randomUUID().toString();
        id=id.replace("-","");
        System.out.println(id);
    }
}
