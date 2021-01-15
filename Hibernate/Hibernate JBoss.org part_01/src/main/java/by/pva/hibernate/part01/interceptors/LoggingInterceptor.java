package by.pva.hibernate.part01.interceptors;

import java.io.Serializable;
import java.util.Arrays;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.jboss.logging.Logger;

@SuppressWarnings("serial")
public class LoggingInterceptor extends EmptyInterceptor {
	
	private final static Logger log = Logger.getLogger("by.pva.hibernate.part01.interceptors");
	
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
		
		System.out.printf("\nEntity {%s}#{%d} changed from {%s} to {%s}\n\n", 
			    entity.getClass().getSimpleName(), 
			    id,
			    Arrays.toString(previousState), 
			    Arrays.toString(currentState));
		
		log.debugv("Entity {0}#{1} changed from {2} to {3}", 
				    entity.getClass().getSimpleName(), 
				    id,
				    Arrays.toString(previousState), 
				    Arrays.toString(currentState));
		
		return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
	}
}
