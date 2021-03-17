package rooftophero.io.toyp2p.utils;

import org.modelmapper.ModelMapper;

// ModelMapper는 @Bean을 통해 사용해야 하므로 @Mock에서 사용하지 못하는 문제로 인해 ModelMap으로 바꿈.
public class ModelMap {
    public static <T> T of(Object source, Class<T> targetClass) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(source, targetClass);
    }
}
