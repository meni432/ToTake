package com.menisamet.totake;

/**
 * Created by meni on 07/09/16.
 */
public class Constants {
    public static final String FIREBASE_ROOT_URL = "https://to-take.firebaseio.com/userData";

    public static final String SUGGESTION_SERVER_ADD_LIKE = "http://178.62.207.178:3000/add-like/";
    public static final String SUGGESTION_SERVER_GET_SUGGESTIONS = "http://178.62.207.178:3000/get-suggestion/";

    // Suggestion grid
    public static final int NUMBER_OF_SUGGESTION_LINES = 3;

    public static final String COUNTRIES = "[ \n" +
            "{\"mNameTextView\": \"Afghanistan\", \"code\": \"AF\"}, \n" +
            "{\"mNameTextView\": \"Åland Islands\", \"code\": \"AX\"}, \n" +
            "{\"mNameTextView\": \"Albania\", \"code\": \"AL\"}, \n" +
            "{\"mNameTextView\": \"Algeria\", \"code\": \"DZ\"}, \n" +
            "{\"mNameTextView\": \"American Samoa\", \"code\": \"AS\"}, \n" +
            "{\"mNameTextView\": \"AndorrA\", \"code\": \"AD\"}, \n" +
            "{\"mNameTextView\": \"Angola\", \"code\": \"AO\"}, \n" +
            "{\"mNameTextView\": \"Anguilla\", \"code\": \"AI\"}, \n" +
            "{\"mNameTextView\": \"Antarctica\", \"code\": \"AQ\"}, \n" +
            "{\"mNameTextView\": \"Antigua and Barbuda\", \"code\": \"AG\"}, \n" +
            "{\"mNameTextView\": \"Argentina\", \"code\": \"AR\"}, \n" +
            "{\"mNameTextView\": \"Armenia\", \"code\": \"AM\"}, \n" +
            "{\"mNameTextView\": \"Aruba\", \"code\": \"AW\"}, \n" +
            "{\"mNameTextView\": \"Australia\", \"code\": \"AU\"}, \n" +
            "{\"mNameTextView\": \"Austria\", \"code\": \"AT\"}, \n" +
            "{\"mNameTextView\": \"Azerbaijan\", \"code\": \"AZ\"}, \n" +
            "{\"mNameTextView\": \"Bahamas\", \"code\": \"BS\"}, \n" +
            "{\"mNameTextView\": \"Bahrain\", \"code\": \"BH\"}, \n" +
            "{\"mNameTextView\": \"Bangladesh\", \"code\": \"BD\"}, \n" +
            "{\"mNameTextView\": \"Barbados\", \"code\": \"BB\"}, \n" +
            "{\"mNameTextView\": \"Belarus\", \"code\": \"BY\"}, \n" +
            "{\"mNameTextView\": \"Belgium\", \"code\": \"BE\"}, \n" +
            "{\"mNameTextView\": \"Belize\", \"code\": \"BZ\"}, \n" +
            "{\"mNameTextView\": \"Benin\", \"code\": \"BJ\"}, \n" +
            "{\"mNameTextView\": \"Bermuda\", \"code\": \"BM\"}, \n" +
            "{\"mNameTextView\": \"Bhutan\", \"code\": \"BT\"}, \n" +
            "{\"mNameTextView\": \"Bolivia\", \"code\": \"BO\"}, \n" +
            "{\"mNameTextView\": \"Bosnia and Herzegovina\", \"code\": \"BA\"}, \n" +
            "{\"mNameTextView\": \"Botswana\", \"code\": \"BW\"}, \n" +
            "{\"mNameTextView\": \"Bouvet Island\", \"code\": \"BV\"}, \n" +
            "{\"mNameTextView\": \"Brazil\", \"code\": \"BR\"}, \n" +
            "{\"mNameTextView\": \"British Indian Ocean Territory\", \"code\": \"IO\"}, \n" +
            "{\"mNameTextView\": \"Brunei Darussalam\", \"code\": \"BN\"}, \n" +
            "{\"mNameTextView\": \"Bulgaria\", \"code\": \"BG\"}, \n" +
            "{\"mNameTextView\": \"Burkina Faso\", \"code\": \"BF\"}, \n" +
            "{\"mNameTextView\": \"Burundi\", \"code\": \"BI\"}, \n" +
            "{\"mNameTextView\": \"Cambodia\", \"code\": \"KH\"}, \n" +
            "{\"mNameTextView\": \"Cameroon\", \"code\": \"CM\"}, \n" +
            "{\"mNameTextView\": \"Canada\", \"code\": \"CA\"}, \n" +
            "{\"mNameTextView\": \"Cape Verde\", \"code\": \"CV\"}, \n" +
            "{\"mNameTextView\": \"Cayman Islands\", \"code\": \"KY\"}, \n" +
            "{\"mNameTextView\": \"Central African Republic\", \"code\": \"CF\"}, \n" +
            "{\"mNameTextView\": \"Chad\", \"code\": \"TD\"}, \n" +
            "{\"mNameTextView\": \"Chile\", \"code\": \"CL\"}, \n" +
            "{\"mNameTextView\": \"China\", \"code\": \"CN\"}, \n" +
            "{\"mNameTextView\": \"Christmas Island\", \"code\": \"CX\"}, \n" +
            "{\"mNameTextView\": \"Cocos (Keeling) Islands\", \"code\": \"CC\"}, \n" +
            "{\"mNameTextView\": \"Colombia\", \"code\": \"CO\"}, \n" +
            "{\"mNameTextView\": \"Comoros\", \"code\": \"KM\"}, \n" +
            "{\"mNameTextView\": \"Congo\", \"code\": \"CG\"}, \n" +
            "{\"mNameTextView\": \"Congo, The Democratic Republic of the\", \"code\": \"CD\"}, \n" +
            "{\"mNameTextView\": \"Cook Islands\", \"code\": \"CK\"}, \n" +
            "{\"mNameTextView\": \"Costa Rica\", \"code\": \"CR\"}, \n" +
            "{\"mNameTextView\": \"Cote D\\\"Ivoire\", \"code\": \"CI\"}, \n" +
            "{\"mNameTextView\": \"Croatia\", \"code\": \"HR\"}, \n" +
            "{\"mNameTextView\": \"Cuba\", \"code\": \"CU\"}, \n" +
            "{\"mNameTextView\": \"Cyprus\", \"code\": \"CY\"}, \n" +
            "{\"mNameTextView\": \"Czech Republic\", \"code\": \"CZ\"}, \n" +
            "{\"mNameTextView\": \"Denmark\", \"code\": \"DK\"}, \n" +
            "{\"mNameTextView\": \"Djibouti\", \"code\": \"DJ\"}, \n" +
            "{\"mNameTextView\": \"Dominica\", \"code\": \"DM\"}, \n" +
            "{\"mNameTextView\": \"Dominican Republic\", \"code\": \"DO\"}, \n" +
            "{\"mNameTextView\": \"Ecuador\", \"code\": \"EC\"}, \n" +
            "{\"mNameTextView\": \"Egypt\", \"code\": \"EG\"}, \n" +
            "{\"mNameTextView\": \"El Salvador\", \"code\": \"SV\"}, \n" +
            "{\"mNameTextView\": \"Equatorial Guinea\", \"code\": \"GQ\"}, \n" +
            "{\"mNameTextView\": \"Eritrea\", \"code\": \"ER\"}, \n" +
            "{\"mNameTextView\": \"Estonia\", \"code\": \"EE\"}, \n" +
            "{\"mNameTextView\": \"Ethiopia\", \"code\": \"ET\"}, \n" +
            "{\"mNameTextView\": \"Falkland Islands (Malvinas)\", \"code\": \"FK\"}, \n" +
            "{\"mNameTextView\": \"Faroe Islands\", \"code\": \"FO\"}, \n" +
            "{\"mNameTextView\": \"Fiji\", \"code\": \"FJ\"}, \n" +
            "{\"mNameTextView\": \"Finland\", \"code\": \"FI\"}, \n" +
            "{\"mNameTextView\": \"France\", \"code\": \"FR\"}, \n" +
            "{\"mNameTextView\": \"French Guiana\", \"code\": \"GF\"}, \n" +
            "{\"mNameTextView\": \"French Polynesia\", \"code\": \"PF\"}, \n" +
            "{\"mNameTextView\": \"French Southern Territories\", \"code\": \"TF\"}, \n" +
            "{\"mNameTextView\": \"Gabon\", \"code\": \"GA\"}, \n" +
            "{\"mNameTextView\": \"Gambia\", \"code\": \"GM\"}, \n" +
            "{\"mNameTextView\": \"Georgia\", \"code\": \"GE\"}, \n" +
            "{\"mNameTextView\": \"Germany\", \"code\": \"DE\"}, \n" +
            "{\"mNameTextView\": \"Ghana\", \"code\": \"GH\"}, \n" +
            "{\"mNameTextView\": \"Gibraltar\", \"code\": \"GI\"}, \n" +
            "{\"mNameTextView\": \"Greece\", \"code\": \"GR\"}, \n" +
            "{\"mNameTextView\": \"Greenland\", \"code\": \"GL\"}, \n" +
            "{\"mNameTextView\": \"Grenada\", \"code\": \"GD\"}, \n" +
            "{\"mNameTextView\": \"Guadeloupe\", \"code\": \"GP\"}, \n" +
            "{\"mNameTextView\": \"Guam\", \"code\": \"GU\"}, \n" +
            "{\"mNameTextView\": \"Guatemala\", \"code\": \"GT\"}, \n" +
            "{\"mNameTextView\": \"Guernsey\", \"code\": \"GG\"}, \n" +
            "{\"mNameTextView\": \"Guinea\", \"code\": \"GN\"}, \n" +
            "{\"mNameTextView\": \"Guinea-Bissau\", \"code\": \"GW\"}, \n" +
            "{\"mNameTextView\": \"Guyana\", \"code\": \"GY\"}, \n" +
            "{\"mNameTextView\": \"Haiti\", \"code\": \"HT\"}, \n" +
            "{\"mNameTextView\": \"Heard Island and Mcdonald Islands\", \"code\": \"HM\"}, \n" +
            "{\"mNameTextView\": \"Holy See (Vatican City State)\", \"code\": \"VA\"}, \n" +
            "{\"mNameTextView\": \"Honduras\", \"code\": \"HN\"}, \n" +
            "{\"mNameTextView\": \"Hong Kong\", \"code\": \"HK\"}, \n" +
            "{\"mNameTextView\": \"Hungary\", \"code\": \"HU\"}, \n" +
            "{\"mNameTextView\": \"Iceland\", \"code\": \"IS\"}, \n" +
            "{\"mNameTextView\": \"India\", \"code\": \"IN\"}, \n" +
            "{\"mNameTextView\": \"Indonesia\", \"code\": \"ID\"}, \n" +
            "{\"mNameTextView\": \"Iran, Islamic Republic Of\", \"code\": \"IR\"}, \n" +
            "{\"mNameTextView\": \"Iraq\", \"code\": \"IQ\"}, \n" +
            "{\"mNameTextView\": \"Ireland\", \"code\": \"IE\"}, \n" +
            "{\"mNameTextView\": \"Isle of Man\", \"code\": \"IM\"}, \n" +
            "{\"mNameTextView\": \"Israel\", \"code\": \"IL\"}, \n" +
            "{\"mNameTextView\": \"Italy\", \"code\": \"IT\"}, \n" +
            "{\"mNameTextView\": \"Jamaica\", \"code\": \"JM\"}, \n" +
            "{\"mNameTextView\": \"Japan\", \"code\": \"JP\"}, \n" +
            "{\"mNameTextView\": \"Jersey\", \"code\": \"JE\"}, \n" +
            "{\"mNameTextView\": \"Jordan\", \"code\": \"JO\"}, \n" +
            "{\"mNameTextView\": \"Kazakhstan\", \"code\": \"KZ\"}, \n" +
            "{\"mNameTextView\": \"Kenya\", \"code\": \"KE\"}, \n" +
            "{\"mNameTextView\": \"Kiribati\", \"code\": \"KI\"}, \n" +
            "{\"mNameTextView\": \"Korea, Democratic People\\\"S Republic of\", \"code\": \"KP\"}, \n" +
            "{\"mNameTextView\": \"Korea, Republic of\", \"code\": \"KR\"}, \n" +
            "{\"mNameTextView\": \"Kuwait\", \"code\": \"KW\"}, \n" +
            "{\"mNameTextView\": \"Kyrgyzstan\", \"code\": \"KG\"}, \n" +
            "{\"mNameTextView\": \"Lao People\\\"S Democratic Republic\", \"code\": \"LA\"}, \n" +
            "{\"mNameTextView\": \"Latvia\", \"code\": \"LV\"}, \n" +
            "{\"mNameTextView\": \"Lebanon\", \"code\": \"LB\"}, \n" +
            "{\"mNameTextView\": \"Lesotho\", \"code\": \"LS\"}, \n" +
            "{\"mNameTextView\": \"Liberia\", \"code\": \"LR\"}, \n" +
            "{\"mNameTextView\": \"Libyan Arab Jamahiriya\", \"code\": \"LY\"}, \n" +
            "{\"mNameTextView\": \"Liechtenstein\", \"code\": \"LI\"}, \n" +
            "{\"mNameTextView\": \"Lithuania\", \"code\": \"LT\"}, \n" +
            "{\"mNameTextView\": \"Luxembourg\", \"code\": \"LU\"}, \n" +
            "{\"mNameTextView\": \"Macao\", \"code\": \"MO\"}, \n" +
            "{\"mNameTextView\": \"Macedonia, The Former Yugoslav Republic of\", \"code\": \"MK\"}, \n" +
            "{\"mNameTextView\": \"Madagascar\", \"code\": \"MG\"}, \n" +
            "{\"mNameTextView\": \"Malawi\", \"code\": \"MW\"}, \n" +
            "{\"mNameTextView\": \"Malaysia\", \"code\": \"MY\"}, \n" +
            "{\"mNameTextView\": \"Maldives\", \"code\": \"MV\"}, \n" +
            "{\"mNameTextView\": \"Mali\", \"code\": \"ML\"}, \n" +
            "{\"mNameTextView\": \"Malta\", \"code\": \"MT\"}, \n" +
            "{\"mNameTextView\": \"Marshall Islands\", \"code\": \"MH\"}, \n" +
            "{\"mNameTextView\": \"Martinique\", \"code\": \"MQ\"}, \n" +
            "{\"mNameTextView\": \"Mauritania\", \"code\": \"MR\"}, \n" +
            "{\"mNameTextView\": \"Mauritius\", \"code\": \"MU\"}, \n" +
            "{\"mNameTextView\": \"Mayotte\", \"code\": \"YT\"}, \n" +
            "{\"mNameTextView\": \"Mexico\", \"code\": \"MX\"}, \n" +
            "{\"mNameTextView\": \"Micronesia, Federated States of\", \"code\": \"FM\"}, \n" +
            "{\"mNameTextView\": \"Moldova, Republic of\", \"code\": \"MD\"}, \n" +
            "{\"mNameTextView\": \"Monaco\", \"code\": \"MC\"}, \n" +
            "{\"mNameTextView\": \"Mongolia\", \"code\": \"MN\"}, \n" +
            "{\"mNameTextView\": \"Montserrat\", \"code\": \"MS\"}, \n" +
            "{\"mNameTextView\": \"Morocco\", \"code\": \"MA\"}, \n" +
            "{\"mNameTextView\": \"Mozambique\", \"code\": \"MZ\"}, \n" +
            "{\"mNameTextView\": \"Myanmar\", \"code\": \"MM\"}, \n" +
            "{\"mNameTextView\": \"Namibia\", \"code\": \"NA\"}, \n" +
            "{\"mNameTextView\": \"Nauru\", \"code\": \"NR\"}, \n" +
            "{\"mNameTextView\": \"Nepal\", \"code\": \"NP\"}, \n" +
            "{\"mNameTextView\": \"Netherlands\", \"code\": \"NL\"}, \n" +
            "{\"mNameTextView\": \"Netherlands Antilles\", \"code\": \"AN\"}, \n" +
            "{\"mNameTextView\": \"New Caledonia\", \"code\": \"NC\"}, \n" +
            "{\"mNameTextView\": \"New Zealand\", \"code\": \"NZ\"}, \n" +
            "{\"mNameTextView\": \"Nicaragua\", \"code\": \"NI\"}, \n" +
            "{\"mNameTextView\": \"Niger\", \"code\": \"NE\"}, \n" +
            "{\"mNameTextView\": \"Nigeria\", \"code\": \"NG\"}, \n" +
            "{\"mNameTextView\": \"Niue\", \"code\": \"NU\"}, \n" +
            "{\"mNameTextView\": \"Norfolk Island\", \"code\": \"NF\"}, \n" +
            "{\"mNameTextView\": \"Northern Mariana Islands\", \"code\": \"MP\"}, \n" +
            "{\"mNameTextView\": \"Norway\", \"code\": \"NO\"}, \n" +
            "{\"mNameTextView\": \"Oman\", \"code\": \"OM\"}, \n" +
            "{\"mNameTextView\": \"Pakistan\", \"code\": \"PK\"}, \n" +
            "{\"mNameTextView\": \"Palau\", \"code\": \"PW\"}, \n" +
            "{\"mNameTextView\": \"Palestinian Territory, Occupied\", \"code\": \"PS\"}, \n" +
            "{\"mNameTextView\": \"Panama\", \"code\": \"PA\"}, \n" +
            "{\"mNameTextView\": \"Papua New Guinea\", \"code\": \"PG\"}, \n" +
            "{\"mNameTextView\": \"Paraguay\", \"code\": \"PY\"}, \n" +
            "{\"mNameTextView\": \"Peru\", \"code\": \"PE\"}, \n" +
            "{\"mNameTextView\": \"Philippines\", \"code\": \"PH\"}, \n" +
            "{\"mNameTextView\": \"Pitcairn\", \"code\": \"PN\"}, \n" +
            "{\"mNameTextView\": \"Poland\", \"code\": \"PL\"}, \n" +
            "{\"mNameTextView\": \"Portugal\", \"code\": \"PT\"}, \n" +
            "{\"mNameTextView\": \"Puerto Rico\", \"code\": \"PR\"}, \n" +
            "{\"mNameTextView\": \"Qatar\", \"code\": \"QA\"}, \n" +
            "{\"mNameTextView\": \"Reunion\", \"code\": \"RE\"}, \n" +
            "{\"mNameTextView\": \"Romania\", \"code\": \"RO\"}, \n" +
            "{\"mNameTextView\": \"Russian Federation\", \"code\": \"RU\"}, \n" +
            "{\"mNameTextView\": \"RWANDA\", \"code\": \"RW\"}, \n" +
            "{\"mNameTextView\": \"Saint Helena\", \"code\": \"SH\"}, \n" +
            "{\"mNameTextView\": \"Saint Kitts and Nevis\", \"code\": \"KN\"}, \n" +
            "{\"mNameTextView\": \"Saint Lucia\", \"code\": \"LC\"}, \n" +
            "{\"mNameTextView\": \"Saint Pierre and Miquelon\", \"code\": \"PM\"}, \n" +
            "{\"mNameTextView\": \"Saint Vincent and the Grenadines\", \"code\": \"VC\"}, \n" +
            "{\"mNameTextView\": \"Samoa\", \"code\": \"WS\"}, \n" +
            "{\"mNameTextView\": \"San Marino\", \"code\": \"SM\"}, \n" +
            "{\"mNameTextView\": \"Sao Tome and Principe\", \"code\": \"ST\"}, \n" +
            "{\"mNameTextView\": \"Saudi Arabia\", \"code\": \"SA\"}, \n" +
            "{\"mNameTextView\": \"Senegal\", \"code\": \"SN\"}, \n" +
            "{\"mNameTextView\": \"Serbia and Montenegro\", \"code\": \"CS\"}, \n" +
            "{\"mNameTextView\": \"Seychelles\", \"code\": \"SC\"}, \n" +
            "{\"mNameTextView\": \"Sierra Leone\", \"code\": \"SL\"}, \n" +
            "{\"mNameTextView\": \"Singapore\", \"code\": \"SG\"}, \n" +
            "{\"mNameTextView\": \"Slovakia\", \"code\": \"SK\"}, \n" +
            "{\"mNameTextView\": \"Slovenia\", \"code\": \"SI\"}, \n" +
            "{\"mNameTextView\": \"Solomon Islands\", \"code\": \"SB\"}, \n" +
            "{\"mNameTextView\": \"Somalia\", \"code\": \"SO\"}, \n" +
            "{\"mNameTextView\": \"South Africa\", \"code\": \"ZA\"}, \n" +
            "{\"mNameTextView\": \"South Georgia and the South Sandwich Islands\", \"code\": \"GS\"}, \n" +
            "{\"mNameTextView\": \"Spain\", \"code\": \"ES\"}, \n" +
            "{\"mNameTextView\": \"Sri Lanka\", \"code\": \"LK\"}, \n" +
            "{\"mNameTextView\": \"Sudan\", \"code\": \"SD\"}, \n" +
            "{\"mNameTextView\": \"Suriname\", \"code\": \"SR\"}, \n" +
            "{\"mNameTextView\": \"Svalbard and Jan Mayen\", \"code\": \"SJ\"}, \n" +
            "{\"mNameTextView\": \"Swaziland\", \"code\": \"SZ\"}, \n" +
            "{\"mNameTextView\": \"Sweden\", \"code\": \"SE\"}, \n" +
            "{\"mNameTextView\": \"Switzerland\", \"code\": \"CH\"}, \n" +
            "{\"mNameTextView\": \"Syrian Arab Republic\", \"code\": \"SY\"}, \n" +
            "{\"mNameTextView\": \"Taiwan, Province of China\", \"code\": \"TW\"}, \n" +
            "{\"mNameTextView\": \"Tajikistan\", \"code\": \"TJ\"}, \n" +
            "{\"mNameTextView\": \"Tanzania, United Republic of\", \"code\": \"TZ\"}, \n" +
            "{\"mNameTextView\": \"Thailand\", \"code\": \"TH\"}, \n" +
            "{\"mNameTextView\": \"Timor-Leste\", \"code\": \"TL\"}, \n" +
            "{\"mNameTextView\": \"Togo\", \"code\": \"TG\"}, \n" +
            "{\"mNameTextView\": \"Tokelau\", \"code\": \"TK\"}, \n" +
            "{\"mNameTextView\": \"Tonga\", \"code\": \"TO\"}, \n" +
            "{\"mNameTextView\": \"Trinidad and Tobago\", \"code\": \"TT\"}, \n" +
            "{\"mNameTextView\": \"Tunisia\", \"code\": \"TN\"}, \n" +
            "{\"mNameTextView\": \"Turkey\", \"code\": \"TR\"}, \n" +
            "{\"mNameTextView\": \"Turkmenistan\", \"code\": \"TM\"}, \n" +
            "{\"mNameTextView\": \"Turks and Caicos Islands\", \"code\": \"TC\"}, \n" +
            "{\"mNameTextView\": \"Tuvalu\", \"code\": \"TV\"}, \n" +
            "{\"mNameTextView\": \"Uganda\", \"code\": \"UG\"}, \n" +
            "{\"mNameTextView\": \"Ukraine\", \"code\": \"UA\"}, \n" +
            "{\"mNameTextView\": \"United Arab Emirates\", \"code\": \"AE\"}, \n" +
            "{\"mNameTextView\": \"United Kingdom\", \"code\": \"GB\"}, \n" +
            "{\"mNameTextView\": \"United States\", \"code\": \"US\"}, \n" +
            "{\"mNameTextView\": \"United States Minor Outlying Islands\", \"code\": \"UM\"}, \n" +
            "{\"mNameTextView\": \"Uruguay\", \"code\": \"UY\"}, \n" +
            "{\"mNameTextView\": \"Uzbekistan\", \"code\": \"UZ\"}, \n" +
            "{\"mNameTextView\": \"Vanuatu\", \"code\": \"VU\"}, \n" +
            "{\"mNameTextView\": \"Venezuela\", \"code\": \"VE\"}, \n" +
            "{\"mNameTextView\": \"Viet Nam\", \"code\": \"VN\"}, \n" +
            "{\"mNameTextView\": \"Virgin Islands, British\", \"code\": \"VG\"}, \n" +
            "{\"mNameTextView\": \"Virgin Islands, U.S.\", \"code\": \"VI\"}, \n" +
            "{\"mNameTextView\": \"Wallis and Futuna\", \"code\": \"WF\"}, \n" +
            "{\"mNameTextView\": \"Western Sahara\", \"code\": \"EH\"}, \n" +
            "{\"mNameTextView\": \"Yemen\", \"code\": \"YE\"}, \n" +
            "{\"mNameTextView\": \"Zambia\", \"code\": \"ZM\"}, \n" +
            "{\"mNameTextView\": \"Zimbabwe\", \"code\": \"ZW\"} \n" +
            "]";
}
