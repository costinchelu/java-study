package chapter_06.fixed.documents;

public class WorkContract implements
        ExportableJson,
        ExportableText,
        ExportablePdf {
    private String content;

    public WorkContract(String content) {
        this.content = content;
    }

    @Override
    public byte[] toPdf() {
        // Test implementation, in reality this would
        // be more complex
        return content.getBytes();
    }

    @Override
    public String toJson() {
        // Test implementation, in reality this would
        // be more complex
        return "{'content':'" + this.content + "'}";
    }

    @Override
    public String toTxt() {
        // Test implementation, in reality this would
        // be more complex
        return this.content;
    }
}
/*
When we create new classes and we need all methods to overwrite, we can just implement all interfaces
 */