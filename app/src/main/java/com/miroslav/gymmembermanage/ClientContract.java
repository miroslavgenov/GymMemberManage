package com.miroslav.gymmembermanage;

import android.provider.BaseColumns;

public final  class ClientContract {

    private ClientContract(){}

    public static class ClientEntry implements BaseColumns{
//        create table if not exists client(
//            client_id integer not null primary key autoincrement,
//            client_name nvarchar(50),
//            client_card_id int,
//            boolean is_client_active default(0) not null,
//            foreign key(client_card_id)
//            references card(card_id)
//        );




        public static final String TABLE_NAME="client";
        public static final String COLUMN_NAME_CLIENT_ID="client_id";
        public static final String COLUMN_NAME_CLIENT_NAME="client_name";
        public static final String COLUMN_NAME_CLIENT_CARD_ID="client_card_id";
        public static final String COLUMN_NAME_IS_CLIENT_ACTIVE="is_client_active";
    }
    public   static  final  String SQL_CREATE_ENTRIES=
            "create table if not exists client(\n" +
                "client_id"+ "integer not null primary key autoincrement,\n" +
                "client_name"+ " nvarchar(50),\n" +
                "client_card_id" + " int,\n" +
                "boolean"+"client_active default(0) not null,\n" +
                "foreign key(client_card_id)\n" +
                "references card(card_id)\n" +
            ");";
    public static final String SQL_DELETE_ENTRIES="drop table if exists "+"client;";
}




































