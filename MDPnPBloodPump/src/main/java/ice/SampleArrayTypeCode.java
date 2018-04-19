
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package ice;
        
import com.rti.dds.typecode.*;


public class SampleArrayTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        StructMember sm[] = new StructMember[9];

        sm[i]=new StructMember("unique_device_identifier",false,(short)-1,true,(TypeCode)ice.UniqueDeviceIdentifierTypeCode.VALUE,0,false); i++;
        sm[i]=new StructMember("metric_id",false,(short)-1,true,(TypeCode)ice.MetricIdentifierTypeCode.VALUE,1,false); i++;
        sm[i]=new StructMember("vendor_metric_id",false,(short)-1,true,(TypeCode)ice.VendorMetricIdentifierTypeCode.VALUE,2,false); i++;
        sm[i]=new StructMember("instance_id",false,(short)-1,true,(TypeCode)ice.InstanceIdentifierTypeCode.VALUE,3,false); i++;
        sm[i]=new StructMember("unit_id",false,(short)-1,true,(TypeCode)ice.UnitIdentifierTypeCode.VALUE,4,false); i++;
        sm[i]=new StructMember("frequency",false,(short)-1,true,(TypeCode)TypeCode.TC_LONG,5,false); i++;
        sm[i]=new StructMember("values",false,(short)-1,false,(TypeCode)ice.ValuesTypeCode.VALUE,6,false); i++;
        sm[i]=new StructMember("device_time",false,(short)-1,false,(TypeCode)ice.Time_tTypeCode.VALUE,7,false); i++;
        sm[i]=new StructMember("presentation_time",false,(short)-1,false,(TypeCode)ice.Time_tTypeCode.VALUE,8,false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("ice::SampleArray",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,sm);
        return tc;
    }
}
