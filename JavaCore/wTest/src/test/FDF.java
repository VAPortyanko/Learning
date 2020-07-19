package test;

public class FDF {
    
    public static Integer getMaxValue(Integer first, Integer second) {
        return new MathInterface<Integer>() {
 
            @Override
            public Integer getMax(Integer first, Integer second) {
                if (first > second)
                    return first;
                return second;
            }
        }.getMax(first, second);
        
    }
   
    static MathInterface<Integer> maxValueLambda = (Integer a, Integer b) -> {
        if(a>b) return a;
        else return b;
    };
   
 
    public static void main(String[] args) {
        System.out.println(FDF.getMaxValue(7, 8));//old school interface variant
       
        System.out.println(maxValueLambda.getMax(7, 4));
    }
 
}

