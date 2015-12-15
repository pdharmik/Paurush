/*
	Contributed by Carlos Nunes, cmanunes@gmail.com, 26.01.2008
*/

var text = new Array();

text['today'] = 'T&auml;n&auml;&auml;n';
text['time'] = 'Hora';

text['dayNamesShort'] = new Array(
'Sun',
'Maa',
'Tii',
'Kes',
'Tor',
'Per',
'Lau'
);
text['dayNames'] = new Array(
'Sunnuntai',
'Maanantai',
'Tiistai',
'Keskiviikko',
'Torstai',
'Perjantai',
'Lauantai'
);

text['monthNamesShort'] = new Array(
'Tammi',
'Helmi',
'Maalis',
'Huhti',
'Touko',
'Kes&auml;',
'Hein&auml;',
'Elo',
'Syys',
'Loka',
'Marras',
'Joulu'
);

text['monthNames'] = new Array(
'Tammikuu',
'Helmikuu',
'Maaliskuu',
'Huhtikuu',
'Toukokuu',
'Kes&auml;kuu',
'Hein&auml;kuu',
'Elokuu',
'Syyskuu',
'Lokakuu',
'Marraskuu',
'Joulukuu'
);

//%n = month; %j = day; %Y = year
text['footerDateFormat'] = '%j.%n.%Y',
text['dateFormat'] = '%j.%n.%Y',
text['footerDefaultText'] = 'Select date Finnish',

text['clear'] = 'Limpar Data',
text['prev_year'] = 'Ano anterior',
text['prev_month'] = 'Mês anterior',
text['next_month'] = 'Próximo mês',
text['next_year'] = 'Próximo ano',
text['close'] = 'Sulje',
text['startDay'] = '1',


// weekend days (0 - sunday, ... 6 - saturday)
text['weekend'] = "0,6";
text['make_first'] = "Começa com %s";


RichCalendar.rc_lang_data['fi'] = text;
