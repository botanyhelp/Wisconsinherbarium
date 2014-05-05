/**Copyright (C) 2009-2014 Thomas Maher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package edu.wisc.botany.Wisconsinherbarium;

import android.app.ListActivity;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.view.Gravity;

public class Families extends ListActivity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            
            //setContentView(R.layout.familylist);
            displayListView();
	}
	
	private void displayListView() {
		List<String> familyList = new ArrayList<String>();
		familyList.add("Acanthaceae");
		familyList.add("Aceraceae");
		familyList.add("Acoraceae");
		familyList.add("Adoxaceae");
		familyList.add("Agavaceae");
		familyList.add("Aizoaceae");
		familyList.add("Alismataceae");
		familyList.add("Amaranthaceae");
		familyList.add("Anacardiaceae");
		familyList.add("Annonaceae");
		familyList.add("Apiaceae");
		familyList.add("Apocynaceae");
		familyList.add("Aquifoliaceae");
		familyList.add("Araceae");
		familyList.add("Araliaceae");
		familyList.add("Araucariaceae");
		familyList.add("Arecaceae");
		familyList.add("Aristolochiaceae");
		familyList.add("Asclepiadaceae");
		familyList.add("Aspleniaceae");
		familyList.add("Asteraceae");
		familyList.add("Azollaceae");
		familyList.add("Balsaminaceae");
		familyList.add("Begoniaceae");
		familyList.add("Berberidaceae");
		familyList.add("Betulaceae");
		familyList.add("Bignoniaceae");
		familyList.add("Blechnaceae");
		familyList.add("Bombacaceae");
		familyList.add("Boraginaceae");
		familyList.add("Brassicaceae");
		familyList.add("Bromeliaceae");
		familyList.add("Buddlejaceae");
		familyList.add("Burseraceae");
		familyList.add("Butomaceae");
		familyList.add("Buxaceae");
		familyList.add("Cabombaceae");
		familyList.add("Cactaceae");
		familyList.add("Caesalpiniaceae");
		familyList.add("Callitrichaceae");
		familyList.add("Calycanthaceae");
		familyList.add("Campanulaceae");
		familyList.add("Cannabaceae");
		familyList.add("Capparidaceae");
		familyList.add("Caprifoliaceae");
		familyList.add("Caryophyllaceae");
		familyList.add("Cecropiaceae");
		familyList.add("Celastraceae");
		familyList.add("Ceratophyllaceae");
		familyList.add("Chenopodiaceae");
		familyList.add("Chrysobalanaceae");
		familyList.add("Cistaceae");
		familyList.add("Clethraceae");
		familyList.add("Clusiaceae");
		familyList.add("Combretaceae");
		familyList.add("Commelinaceae");
		familyList.add("Convolvulaceae");
		familyList.add("Cornaceae");
		familyList.add("Costaceae");
		familyList.add("Crassulaceae");
		familyList.add("Cucurbitaceae");
		familyList.add("Cupressaceae");
		familyList.add("Cuscutaceae");
		familyList.add("Cyperaceae");
		familyList.add("Cyrillaceae");
		familyList.add("Datiscaceae");
		familyList.add("Dennstaedtiaceae");
		familyList.add("Dichapetalaceae");
		familyList.add("Dioscoreaceae");
		familyList.add("Dipsacaceae");
		familyList.add("Droseraceae");
		familyList.add("Dryopteridaceae");
		familyList.add("Ebenaceae");
		familyList.add("Elaeagnaceae");
		familyList.add("Elaeocarpaceae");
		familyList.add("Elatinaceae");
		familyList.add("Empetraceae");
		familyList.add("Ephedraceae");
		familyList.add("Equisetaceae");
		familyList.add("Ericaceae");
		familyList.add("Eriocaulaceae");
		familyList.add("Euphorbiaceae");
		familyList.add("Fabaceae");
		familyList.add("Fagaceae");
		familyList.add("Flacourtiaceae");
		familyList.add("Fouquieriaceae");
		familyList.add("Fumariaceae");
		familyList.add("Garryaceae");
		familyList.add("Gentianaceae");
		familyList.add("Geraniaceae");
		familyList.add("Gesneriaceae");
		familyList.add("Goodeniaceae");
		familyList.add("Grossulariaceae");
		familyList.add("Haemodoraceae");
		familyList.add("Haloragaceae");
		familyList.add("Hamamelidaceae");
		familyList.add("Heliconiaceae");
		familyList.add("Hernandiaceae");
		familyList.add("Hippocastanaceae");
		familyList.add("Hippocrateaceae");
		familyList.add("Hippuridaceae");
		familyList.add("Hydrangeaceae");
		familyList.add("Hydrocharitaceae");
		familyList.add("Hydrophyllaceae");
		familyList.add("Hypericaceae");
		familyList.add("Icacinaceae");
		familyList.add("Iridaceae");
		familyList.add("Isoetaceae");
		familyList.add("Juglandaceae");
		familyList.add("Juncaceae");
		familyList.add("Juncaginaceae");
		familyList.add("Krameriaceae");
		familyList.add("Lacistemataceae");
		familyList.add("Lamiaceae");
		familyList.add("Lardizabalaceae");
		familyList.add("Lauraceae");
		familyList.add("Lecythidaceae");
		familyList.add("Lemnaceae");
		familyList.add("Lentibulariaceae");
		familyList.add("Liliaceae");
		familyList.add("Limnanthaceae");
		familyList.add("Linaceae");
		familyList.add("Loasaceae");
		familyList.add("Lobeliaceae");
		familyList.add("Loranthaceae");
		familyList.add("Lycopodiaceae");
		familyList.add("Lythraceae");
		familyList.add("Magnoliaceae");
		familyList.add("Malpighiaceae");
		familyList.add("Malvaceae");
		familyList.add("Marantaceae");
		familyList.add("Marcgraviaceae");
		familyList.add("Marsileaceae");
		familyList.add("Melastomataceae");
		familyList.add("Meliaceae");
		familyList.add("Menispermaceae");
		familyList.add("Menyanthaceae");
		familyList.add("Mimosaceae");
		familyList.add("Molluginaceae");
		familyList.add("Monimiaceae");
		familyList.add("Monotropaceae");
		familyList.add("Moraceae");
		familyList.add("Myricaceae");
		familyList.add("Myristicaceae");
		familyList.add("Myrsinaceae");
		familyList.add("Myrtaceae");
		familyList.add("Najadaceae");
		familyList.add("Nelumbonaceae");
		familyList.add("Nyctaginaceae");
		familyList.add("Nymphaeaceae");
		familyList.add("Nyssaceae");
		familyList.add("Ochnaceae");
		familyList.add("Olacaceae");
		familyList.add("Oleaceae");
		familyList.add("Onagraceae");
		familyList.add("Ophioglossaceae");
		familyList.add("Orchidaceae");
		familyList.add("Orobanchaceae");
		familyList.add("Osmundaceae");
		familyList.add("Oxalidaceae");
		familyList.add("Paeoniaceae");
		familyList.add("Papaveraceae");
		familyList.add("Passifloraceae");
		familyList.add("Pedaliaceae");
		familyList.add("Phytolaccaceae");
		familyList.add("Pinaceae");
		familyList.add("Piperaceae");
		familyList.add("Plantaginaceae");
		familyList.add("Platanaceae");
		familyList.add("Poaceae");
		familyList.add("Podocarpaceae");
		familyList.add("Podostemaceae");
		familyList.add("Polemoniaceae");
		familyList.add("Polygalaceae");
		familyList.add("Polygonaceae");
		familyList.add("Polypodiaceae");
		familyList.add("Pontederiaceae");
		familyList.add("Portulacaceae");
		familyList.add("Potamogetonaceae");
		familyList.add("Primulaceae");
		familyList.add("Proteaceae");
		familyList.add("Pteridaceae");
		familyList.add("Pyrolaceae");
		familyList.add("Quiinaceae");
		familyList.add("Ranunculaceae");
		familyList.add("Resedaceae");
		familyList.add("Rhamnaceae");
		familyList.add("Rhizophoraceae");
		familyList.add("Rosaceae");
		familyList.add("Rubiaceae");
		familyList.add("Ruppiaceae");
		familyList.add("Rutaceae");
		familyList.add("Salicaceae");
		familyList.add("Santalaceae");
		familyList.add("Sapindaceae");
		familyList.add("Sapotaceae");
		familyList.add("Sarraceniaceae");
		familyList.add("Saururaceae");
		familyList.add("Saxifragaceae");
		familyList.add("Scheuchzeriaceae");
		familyList.add("Scrophulariaceae");
		familyList.add("Selaginellaceae");
		familyList.add("Simaroubaceae");
		familyList.add("Smilacaceae");
		familyList.add("Solanaceae");
		familyList.add("Sonneratiaceae");
		familyList.add("Sparganiaceae");
		familyList.add("Staphyleaceae");
		familyList.add("Sterculiaceae");
		familyList.add("Symplocaceae");
		familyList.add("Tamaricaceae");
		familyList.add("Taxaceae");
		familyList.add("Taxodiaceae");
		familyList.add("Thelypteridaceae");
		familyList.add("Theophrastaceae");
		familyList.add("Thymelaeaceae");
		familyList.add("Tiliaceae");
		familyList.add("Trapaceae");
		familyList.add("Typhaceae");
		familyList.add("Ulmaceae");
		familyList.add("Urticaceae");
		familyList.add("Valerianaceae");
		familyList.add("Verbenaceae");
		familyList.add("Violaceae");
		familyList.add("Viscaceae");
		familyList.add("Vitaceae");
		familyList.add("Vittariaceae");
		familyList.add("Xyridaceae");
		familyList.add("Zannichelliaceae");
		familyList.add("Zygophyllaceae");
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.familytextview, familyList);
		setListAdapter(dataAdapter);
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
	    listView.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		        Intent i = new Intent("");
		        String familyChoice = ((TextView) view).getText().toString();
		        i.setData(Uri.parse(familyChoice));
		        setResult(RESULT_OK, i);
		        finish(); 
		    }
        });
	}
}
