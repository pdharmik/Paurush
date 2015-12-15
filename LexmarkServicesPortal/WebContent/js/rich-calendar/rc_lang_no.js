/*
	Contributed by Carlos Nunes, cmanunes@gmail.com, 26.01.2008
*/

var text = new Array();

text['today'] = 'I dag';
text['time'] = 'Hora';

text['dayNamesShort'] = new Array(
'Søn',
'Man',
'Tir',
'Ons',
'Tor',
'Fre',
'Lør'
);
text['dayNames'] = new Array(
'Søndag',
'Mandag',
'Tirsdag',
'Onsdag',
'Torsdag',
'Fredag',
'Lørdag'
);

text['monthNamesShort'] = new Array(
'Jan',
'Feb',
'Mar',
'Apr',
'Mai',
'Jun',
'Jul',
'Aug',
'Sep',
'Oct',
'Nov',
'Des'
);

text['monthNames'] = new Array(
'Januar',
'Februar',
'Mars',
'April',
'Mai',
'Juni',
'Juli',
'August',
'September',
'Oktober',
'November',
'Desember'
);

//%n = month; %j = day; %Y = year
text['footerDateFormat'] = '%Y-%n-%j',
text['dateFormat'] = '%Y-%n-%j',
text['footerDefaultText'] = 'Select date Norwegian',

text['clear'] = 'Limpar Data',
text['prev_year'] = 'Ano anterior',
text['prev_month'] = 'Mês anterior',
text['next_month'] = 'Próximo mês',
text['next_year'] = 'Próximo ano',
text['close'] = 'Lukk',
text['startDay'] = '0',


// weekend days (0 - sunday, ... 6 - saturday)
text['weekend'] = "0,6";
text['make_first'] = "Começa com %s";


RichCalendar.rc_lang_data['no'] = text;
