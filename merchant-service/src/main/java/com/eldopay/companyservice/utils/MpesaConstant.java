package com.eldopay.companyservice.utils;

import okhttp3.MediaType;

public class MpesaConstant {
    public static final String BASIC_AUTH_STRING = "Basic";
    public static final String BEARER_AUTH_STRING = "Bearer";
    public static final String AUTHORIZATION_HEADER_STRING = "authorization";
    public static final String CACHE_CONTROL_HEADER = "cache-control";
    public static final String CACHE_CONTROL_HEADER_VALUE = "no-cache";
    public static MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    public static final String TRANSACTION_STATUS_QUERY_COMMAND = "TransactionStatusQuery";
    public static final String ACCOUNT_BALANCE_COMMAND = "AccountBalance";
    public static final String CUSTOMER_PAYBILL_ONLINE = "CustomerPayBillOnline";
    public static final String TRANSACTION_STATUS_VALUE = "Transaction Status";

    public static final String MSISDN_IDENTIFIER = "1";
    public static final String TILL_NUMBER_IDENTIFIER = "2";
    public static final String SHORT_CODE_IDENTIFIER = "4";
    public  static String APP_KEY ="SHM3dTGGSm2r92kGlLAS8YSVGI05ky4I"; //replace with your app api key
    public static String APP_SECRET="CudGQ9X6g05Jk8SC"; //replace with your app api secret

}