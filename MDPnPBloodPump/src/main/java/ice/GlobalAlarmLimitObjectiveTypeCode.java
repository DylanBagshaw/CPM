
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package ice;
        
import com.rti.dds.typecode.*;


public class GlobalAlarmLimitObjectiveTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        StructMember sm[] = new StructMember[4];

        sm[i]=new StructMember("metric_id",false,(short)-1,true,(TypeCode)ice.MetricIdentifierTypeCode.VALUE,0,false); i++;
        sm[i]=new StructMember("limit_type",false,(short)-1,true,(TypeCode)ice.LimitTypeTypeCode.VALUE,1,false); i++;
        sm[i]=new StructMember("unit_identifier",false,(short)-1,false,(TypeCode)ice.UnitIdentifierTypeCode.VALUE,2,false); i++;
        sm[i]=new StructMember("value",false,(short)-1,false,(TypeCode)TypeCode.TC_FLOAT,3,false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("ice::GlobalAlarmLimitObjective",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,sm);
        return tc;
    }
}
