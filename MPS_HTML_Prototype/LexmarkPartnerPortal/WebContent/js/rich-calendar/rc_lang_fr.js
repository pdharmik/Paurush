/*
	Contributed by Yves Vrod, yv@auvillage.net, 06.04.2009
*/

var text = new Array();

text['today'] = 'Ce jour';
text['time'] = 'Heure';

text['dayNamesShort'] = new Array(
'Dim',
'Lun',
'Mar',
'Mer',
'Jeu',
'Ven',
'Sam'
);
text['dayNames'] = new Array(
'Dimanche',
'Lundi',
'Mardi',
'Mercredi',
'Jeudi',
'Vendredi',
'Samedi'
);

text['monthNamesShort'] = new Array(
'Jan',
'Fév',
'Mar',
'Avr',
'Mai',
'Jun',
'Jul',
'Aoû',
'Sep',
'Oct',
'Nov',
'Déc'
);

text['monthNames'] = new Array(
'Janvier',
'Février',
'Mars',
'Avril',
'Mai',
'Juin',
'Juillet',
'Août',
'Septembre',
'Octobre',
'Novembre',
'Décembre'
);


//%m = month; %d = day; %Y = year
text['footerDateFormat'] = '%d/%m/%Y',
text['dateFormat'] = '%d/%m/%Y',
text['footerDefaultText'] = 'Choisir la date',

text['clear'] = 'Annuler',
text['prev_year'] = 'Année précedente',
text['prev_month'] = 'Mois précédent',
text['next_month'] = 'Mois prochain',
text['next_year'] = 'Année prochaine',
text['close'] = 'Fermer', 


// weekend days (0 - sunday, ... 6 - saturday)
text['weekend'] = "0,6";
text['make_first'] = "1<sup>er</sup> jour %s";
text['startDay'] = 1;

//select and cancel
text['select'] = "Choisir";
text['cancel'] = "Annuler";

RichCalendar.rc_lang_data['fr'] = text;