package logic.server.com;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class MapperSingleton implements ContextResolver<ObjectMapper> {
	private static ObjectMapper mapper = null;
	private MapperSingleton(){}
	
	public synchronized static ObjectMapper getMapper(){
		if(mapper == null){
			mapper = new ObjectMapper();
			return mapper;
		}
		else{
			return mapper;
		}
	}

	@Override
	public ObjectMapper getContext(Class<?> aClass) {
		return mapper;
	}
}
