/*
	Contributed by Carlos Nunes, cmanunes@gmail.com, 26.01.2008
*/

var text = new Array();

text['today'] = 'Oggi';
text['time'] = 'Hora';

text['dayNamesShort'] = new Array(
'Dom',
'Lun',
'Mar',
'Mer',
'Gio',
'Ven',
'Sab'
);
text['dayNames'] = new Array(
'Domenica',
'Luned&#236',
'Marted&#236',
'Mercoled&#236',
'Gioved&#236',
'Venerd&#236',
'Sabato'
);

text['monthNamesShort'] = new Array(
'Gen',
'Feb',
'Mar',
'Apr',
'Mag',
'Giu',
'Lug',
'Ago',
'Set',
'Ott',
'Nov',
'Dic'
);

text['monthNames'] = new Array(
'Gennaio',
'Febbraio',
'Marzo',
'Aprile',
'Maggio',
'Giugno',
'Luglio',
'Agosto',
'Settembre',
'Ottobre',
'Novembre',
'Dicembre'
);

//%n = month; %j = day; %Y = year
text['footerDateFormat'] = '%j/%n/%Y',
text['dateFormat'] = '%j/%n/%Y',
text['footerDefaultText'] = 'Seleziona Data',

text['clear'] = 'Limpar Data',
text['prev_year'] = 'Ano anterior',
text['prev_month'] = 'Mês anterior',
text['next_month'] = 'Próximo mês',
text['next_year'] = 'Próximo ano',
text['close'] = 'Chiudi',
text['startDay'] = '1',


// weekend days (0 - sunday, ... 6 - saturday)
text['weekend'] = "0,6";
text['make_first'] = "Começa com %s";


RichCalendar.rc_lang_data['it'] = text;
