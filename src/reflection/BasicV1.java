package reflection;

import reflection.data.BasicData;

public class BasicV1 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 클래스 메타데이터 조회 방법 3가지

        // 1. 클래스에서 찾기
        Class<BasicData> basicDataClass = BasicData.class;
        System.out.println("basicDataClass = "+basicDataClass);

        // 2. 인스턴스에서 찾기
        BasicData basicInstnace = new BasicData();
        Class<? extends BasicData> basicDataClass2 = basicInstnace.getClass();
        System.out.println("basicDataClas2 = "+ basicDataClass2);

        // 3. 문자로 찾기
        String className = "reflection.data.BasicData";
        Class<?> basicDataClass3 = Class.forName(className);
        System.out.println("basicDataClas3 = "+ basicDataClass3);



    }
}
