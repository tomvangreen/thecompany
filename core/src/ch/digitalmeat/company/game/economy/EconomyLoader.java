package ch.digitalmeat.company.game.economy;

import ch.digitalmeat.company.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonValue;

public class EconomyLoader {

	private Economy economy;

	public Economy load(JsonValue root) {
		Economy economy = new Economy();
		this.economy = economy;

		loadGoods(root.get("goods"));
		loadProfessions(root.get("professions"));
		loadEducations(root.get("educations"));
		loadIndustryProcesses(root.get("processes"));
		loadBuildings(root.get("buildings"));

		this.economy = null;
		return economy;
	}

	private void loadGoods(JsonValue node) {
		node = node.child;
		while (node != null) {
			loadGood(node);
			node = node.next;
		}
	}

	private void loadGood(JsonValue node) {
		Good good = new Good(economy);
		good.read(Assets.json, node);
		economy.goods.put(good.id, good);
		Gdx.app.log("EconomyLoader", "Loaded: " + good);
	}

	private void loadProfessions(JsonValue node) {
		node = node.child;
		while (node != null) {
			loadProfession(node);
			node = node.next;
		}
	}

	private void loadProfession(JsonValue node) {
		Profession profession = new Profession(economy);
		profession.read(Assets.json, node);
		economy.professions.put(profession.id, profession);
		Gdx.app.log("EconomyLoader", "Loaded: " + profession);
	}

	private void loadEducations(JsonValue node) {
		node = node.child;
		while (node != null) {
			loadEducation(node);
			node = node.next;
		}
	}

	private void loadEducation(JsonValue node) {
		Education education = new Education(economy);
		education.read(Assets.json, node);
		economy.educations.put(education.id, education);
		Gdx.app.log("EconomyLoader", "Loaded: " + education);
	}

	private void loadIndustryProcesses(JsonValue node) {
		node = node.child;
		while (node != null) {
			loadIndustryProcess(node);
			node = node.next;
		}
	}

	private void loadIndustryProcess(JsonValue node) {
		IndustryProcess industryProcess = new IndustryProcess(economy);
		industryProcess.read(Assets.json, node);
		economy.processes.put(industryProcess.id, industryProcess);
		Gdx.app.log("EconomyLoader", "Loaded: " + industryProcess);
	}

	private void loadBuildings(JsonValue node) {
		node = node.child;
		while (node != null) {
			loadBuilding(node);
			node = node.next;
		}
	}

	private void loadBuilding(JsonValue node) {
		Building building = new Building(economy);
		building.read(Assets.json, node);
		economy.buildings.put(building.id, building);
		Gdx.app.log("EconomyLoader", "Loaded: " + building);
	}

}
