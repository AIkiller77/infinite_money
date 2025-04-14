package com.android.cglib.dx.a.b;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.InputDeviceCompat;
import com.android.cglib.dx.d.r;

public enum y implements r {
    TYPE_HEADER_ITEM(0, "header_item"),
    TYPE_STRING_ID_ITEM(1, "string_id_item"),
    TYPE_TYPE_ID_ITEM(2, "type_id_item"),
    TYPE_PROTO_ID_ITEM(3, "proto_id_item"),
    TYPE_FIELD_ID_ITEM(4, "field_id_item"),
    TYPE_METHOD_ID_ITEM(5, "method_id_item"),
    TYPE_CLASS_DEF_ITEM(6, "class_def_item"),
    TYPE_MAP_LIST(4096, "map_list"),
    TYPE_TYPE_LIST(FragmentTransaction.TRANSIT_FRAGMENT_OPEN, "type_list"),
    TYPE_ANNOTATION_SET_REF_LIST(InputDeviceCompat.SOURCE_TOUCHSCREEN, "annotation_set_ref_list"),
    TYPE_ANNOTATION_SET_ITEM(FragmentTransaction.TRANSIT_FRAGMENT_FADE, "annotation_set_item"),
    TYPE_CLASS_DATA_ITEM(8192, "class_data_item"),
    TYPE_CODE_ITEM(8193, "code_item"),
    TYPE_STRING_DATA_ITEM(8194, "string_data_item"),
    TYPE_DEBUG_INFO_ITEM(8195, "debug_info_item"),
    TYPE_ANNOTATION_ITEM(8196, "annotation_item"),
    TYPE_ENCODED_ARRAY_ITEM(8197, "encoded_array_item"),
    TYPE_ANNOTATIONS_DIRECTORY_ITEM(8198, "annotations_directory_item"),
    TYPE_MAP_ITEM(-1, "map_item"),
    TYPE_TYPE_ITEM(-1, "type_item"),
    TYPE_EXCEPTION_HANDLER_ITEM(-1, "exception_handler_item"),
    TYPE_ANNOTATION_SET_REF_ITEM(-1, "annotation_set_ref_item");
    
    private final int w;
    private final String x;
    private final String y;

    private y(int i, String str) {
        this.w = i;
        this.x = str;
        this.y = (str.endsWith("_item") ? str.substring(0, str.length() - 5) : str).replace('_', ' ');
    }

    public String a_() {
        return this.y;
    }

    public int b() {
        return this.w;
    }

    public String c() {
        return this.x;
    }
}
