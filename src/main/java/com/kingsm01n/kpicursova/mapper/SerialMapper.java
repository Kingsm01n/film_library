package com.kingsm01n.kpicursova.mapper;

import com.kingsm01n.kpicursova.dto.SerialDto;
import com.kingsm01n.kpicursova.entity.Serial;
import org.mapstruct.Mapper;

@Mapper
public interface SerialMapper extends BaseMapper<SerialDto, Serial> {
}
