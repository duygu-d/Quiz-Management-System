package org.quiz;

import java.util.UUID;

public final class Guid {
    private Guid() {
    }
    public static String GenerateGuid()
    {
       UUID uuid = UUID.randomUUID();
       return uuid.toString();
    }

    public static String GenerateAdminGuid()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("admin");
        sb.append(GenerateGuid());
        return sb.toString();
    }
}
