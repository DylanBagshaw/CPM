
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package ice;
        
import com.rti.dds.typecode.*;


public class ImageTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        StructMember sm[] = new StructMember[2];

        sm[i]=new StructMember("content_type",false,(short)-1,false,(TypeCode)new TypeCode(TCKind.TK_STRING,64),0,false); i++;
        sm[i]=new StructMember("image",false,(short)-1,false,(TypeCode)ice.ImageDataTypeCode.VALUE,1,false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("ice::Image",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,sm);
        return tc;
    }
}
