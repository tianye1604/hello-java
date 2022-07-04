package com.study.anything;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class TestCreateFile {

    public static void main(String[] args) throws IOException {
       List<String> contents = Files.readAllLines(Paths.get("/Users/tianshujian1/source/saas_base/saas-base-service/src/main/java/com/jd/jxxn/saas/function/listener/binlake/sub/SaasFunctionGrantAuthorityTableHandler1.java"));

        for (int n = 2; n <= 64; n++) {
            List<String> newStr = new ArrayList<>();
            for (int i = 0; i < contents.size(); i++) {
                String line = contents.get(i);
                if (line.indexOf("saas_function_grant_authority_1") > 0) {
                    line = line.replace("saas_function_grant_authority_1","saas_function_grant_authority_"+n);
                }
                if (line.indexOf("SaasFunctionGrantAuthorityTableHandler1") > 0) {
                    line = line.replace("SaasFunctionGrantAuthorityTableHandler1","SaasFunctionGrantAuthorityTableHandler"+n);
                }
                newStr.add(line);
            }
            Files.write(Paths.get("/Users/tianshujian1/source/saas_base/saas-base-service/src/main/java/com/jd/jxxn/saas/function/listener/binlake/sub/SaasFunctionGrantAuthorityTableHandler"+n+".java"),newStr, StandardOpenOption.CREATE_NEW);
        }


    }
}
