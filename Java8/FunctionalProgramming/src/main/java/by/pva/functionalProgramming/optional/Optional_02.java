package by.pva.functionalProgramming.optional;

public class Optional_02 {
	
//	public int getMobileScreenWidth(Mobile mobile){
//
//		if(mobile != null){
//			DisplayFeatures dfeatures = mobile.getDisplayFeatures();
//			if(dfeatures != null){
//				ScreenResolution resolution = dfeatures.getResolution();
//				if(resolution != null){
//					return resolution.getWidth();
//				}
//			}
//		}
//		return 0;
//	}
	
//	  public Integer getMobileScreenWidth(Optional<Mobile> mobile){
//			return mobile.flatMap(Mobile::getDisplayFeatures)
//				         .flatMap(DisplayFeatures::getResolution)
//				         .map(ScreenResolution::getWidth)
//				         .orElse(0);
//
//	  }
}

// https://mkyong.com/java8/java-8-optional-in-depth/