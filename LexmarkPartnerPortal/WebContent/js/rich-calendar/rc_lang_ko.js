// TODO: translate to Korea, modify appropriately from footerDateFormat to startDay
var text = new Array();

text['today'] = '\uC624\uB298˜';
text['time'] = '\uC2DC\uAC04';

text['dayNamesShort'] = new Array(
		'\uc77c',
		'\uc6d4',
		'\ud654',
		'\uc218',
		'\ubaa9',
		'\uae08',
		'\ud1a0'
);
text['dayNames'] = new Array(
		'\uc77c',
		'\uc6d4',
		'\ud654',
		'\uc218',
		'\ubaa9',
		'\uae08',
		'\ud1a0'
);

text['monthNamesShort'] = new Array(
		'1\uc6d4',
		'2\uc6d4',
		'3\uc6d4',
		'4\uc6d4',
		'5\uc6d4',
		'6\uc6d4',
		'7\uc6d4',
		'8\uc6d4',
		'9\uc6d4',
		'10\uc6d4',
		'11\uc6d4',
		'12\uc6d4'
);

text['monthNames'] = new Array(
		'1\uc6d4',
		'2\uc6d4',
		'3\uc6d4',
		'4\uc6d4',
		'5\uc6d4',
		'6\uc6d4',
		'7\uc6d4',
		'8\uc6d4',
		'9\uc6d4',
		'10\uc6d4',
		'11\uc6d4',
		'12\uc6d4'
);

//%m = month; %d = day; %Y = year
text['footerDateFormat'] = '%Y.%m.%d',
text['dateFormat'] = '%Y.%m.%d',
text['footerDefaultText'] = '\uB0A0\uC9DC\u0020\uC120\uD0DD',

text['clear'] = '\uCDE8\uC18C\u0020\uB0A0\uC9DC',
text['prev_year'] = '\uC774\uC804\u0020\uC5F0\uB3C4',
text['prev_month'] = '\uC774\uC804\u0020\uB2EC',
text['next_month'] = '\uB2E4\uC74C\u0020\uB2EC',
text['next_year'] = '\uB0B4\uB144',
text['close'] = '\uB2EB\uAE30',


// weekend days (0 - sunday, ... 6 - saturday)
text['weekend'] = "0,6";
text['make_first'] = "\uC2DC\uC791 %s";
text['startDay'] = 0;

//select and cancel
text['select'] = '\uc120\ud0dd';
text['cancel'] = '\ucde8\uc18c';

RichCalendar.rc_lang_data['ko'] = text;
