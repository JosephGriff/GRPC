// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PasswordServ.proto

package ie.gmit.ds;

public final class PasswordServ {
  private PasswordServ() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ie_gmit_ds_PasswordHashRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ie_gmit_ds_PasswordHashRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ie_gmit_ds_PasswordHashResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ie_gmit_ds_PasswordHashResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ie_gmit_ds_PasswordValidateRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ie_gmit_ds_PasswordValidateRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022PasswordServ.proto\022\nie.gmit.ds\032\036google" +
      "/protobuf/wrappers.proto\032\033google/protobu" +
      "f/empty.proto\"7\n\023PasswordHashRequest\022\016\n\006" +
      "userId\030\001 \001(\005\022\020\n\010password\030\002 \001(\t\"L\n\024Passwo" +
      "rdHashResponse\022\016\n\006userId\030\001 \001(\005\022\026\n\016hashed" +
      "Password\030\002 \001(\014\022\014\n\004salt\030\003 \001(\014\"Q\n\027Password" +
      "ValidateRequest\022\020\n\010password\030\001 \001(\t\022\026\n\016has" +
      "hedPassword\030\002 \001(\014\022\014\n\004salt\030\003 \001(\0142\251\001\n\017Pass" +
      "wordService\022I\n\004hash\022\037.ie.gmit.ds.Passwor" +
      "dHashRequest\032 .ie.gmit.ds.PasswordHashRe" +
      "sponse\022K\n\010validate\022#.ie.gmit.ds.Password" +
      "ValidateRequest\032\032.google.protobuf.BoolVa" +
      "lueB\016\n\nie.gmit.dsP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.WrappersProto.getDescriptor(),
          com.google.protobuf.EmptyProto.getDescriptor(),
        });
    internal_static_ie_gmit_ds_PasswordHashRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ie_gmit_ds_PasswordHashRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ie_gmit_ds_PasswordHashRequest_descriptor,
        new java.lang.String[] { "UserId", "Password", });
    internal_static_ie_gmit_ds_PasswordHashResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_ie_gmit_ds_PasswordHashResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ie_gmit_ds_PasswordHashResponse_descriptor,
        new java.lang.String[] { "UserId", "HashedPassword", "Salt", });
    internal_static_ie_gmit_ds_PasswordValidateRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_ie_gmit_ds_PasswordValidateRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ie_gmit_ds_PasswordValidateRequest_descriptor,
        new java.lang.String[] { "Password", "HashedPassword", "Salt", });
    com.google.protobuf.WrappersProto.getDescriptor();
    com.google.protobuf.EmptyProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
