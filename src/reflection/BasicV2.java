package reflection;

import reflection.data.BasicData;

public class BasicV2 {

    public static void main(String[] args) {
        Class<BasicData> basicData =  BasicData.class ;

        System.out.println("basicData.getName() = "+ basicData.getName());
        System.out.println("basicData.getSimpleName() = "+ basicData.getSimpleName());
        System.out.println("basicData.getPackage() = "+ basicData.getPackage());

        System.out.println("basicData.getSuperclass() = "+ basicData.getSuperclass());
    }
}
