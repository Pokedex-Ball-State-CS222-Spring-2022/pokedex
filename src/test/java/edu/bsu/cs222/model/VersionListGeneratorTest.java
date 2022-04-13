package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class VersionListGeneratorTest {
    @Test
    public void testGetListOfAllVersions_justGenOne_sizeIs3() {
        TestURLProcessor testURLProcessor = new TestURLProcessor();
        VersionListGenerator versionListGenerator = new VersionListGenerator(testURLProcessor);
        List<Version> genOneVersionList = versionListGenerator.getListOfAllVersions();
        Assertions.assertEquals(3, genOneVersionList.size());
    }
}
