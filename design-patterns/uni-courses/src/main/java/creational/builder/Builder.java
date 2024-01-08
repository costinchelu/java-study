package creational.builder;

public interface Builder {

    <T extends IBuildMe> T build();
}
