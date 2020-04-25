import json
import urllib.request

with open('D:\MrAsieru\Documents\GitHub\PMOBO-CARD-JITSU\CardJitsu\src\Kartak\cards_eu.json', 'r') as f:
    c_dict = json.load(f)["KartenLista"]
def idEguneratu():
    i = 0
    print("[")
    for card in c_dict:
        print("{")
        print("\"card_id\": \"%s\","%i)
        print("\"set_id\": \"%s\","%card["set_id"])
        print("\"power_id\": \"%s\","%card["power_id"])
        print("\"element\": \"%s\","%card["element"])
        print("\"name\": \"%s\","%card["name"])
        print("\"color\": \"%s\","%card["color"])
        print("\"value\": \"%s\","%card["value"])
        print("\"asset\": \"%s\","%card["asset"])
        print("\"description\": \"%s\","%card["description"])     #efektuaItzuli(card)    
        print("\"is_active\": \"%s\""%card["is_active"])
        print("},")
        i = i+1
    print("]")
def idKonprobatu():
    i = 1
    for card in c_dict:
        print("%s, %s"%(card["card_id"],i))
        i = i+1
def izenaKonprobatu():
    for card in c_dict:
        if(card["name"] != card["label"] or card["name"] != card["prompt"] or card["label"] != card["prompt"]):
           print(card["card_id"])
def assetKonprobatu():
    for card in c_dict:
        if(card["asset"] != ""):
            print(card["card_id"])
def kartenKopuruak():
    normala = 0
    berezia = 0

    sua = 0
    ura = 0
    elurra = 0

    gorria = 0
    urdina = 0
    horia = 0
    berdea = 0
    laranja = 0
    morea = 0

    altuak = 0

    set1 = 0
    set2 = 0
    set3 = 0
    set4 = 0
    set5 = 0
    set6 = 0
    set7 = 0
    set8 = 0

    deskripzioa = 0
    for card in c_dict:
        if card["power_id"] == "0":
            normala = normala + 1
        else:
            berezia = berezia + 1

        if card["element"] == "f":
            sua = sua + 1
        elif card["element"] == "w":
            ura = ura + 1
        elif card["element"] == "s":
            elurra = elurra + 1

        if card["color"] == "r":
            gorria = gorria + 1
        elif card["color"] == "b":
            urdina = urdina + 1
        elif card["color"] == "y":
            horia = horia + 1
        elif card["color"] == "g":
            berdea = berdea + 1
        elif card["color"] == "o":
            laranja = laranja + 1
        elif card["color"] == "p":
            morea = morea + 1

        if card["set_id"] == "1":
            set1 = set1 + 1
        elif card["set_id"] == "2":
            set2 = set2 + 1
        elif card["set_id"] == "3":
            set3 = set3 + 1
        elif card["set_id"] == "4":
            set4 = set4 + 1
        elif card["set_id"] == "5":
            set5 = set5 + 1
        elif card["set_id"] == "6":
            set6 = set6 + 1
        elif card["set_id"] == "7":
            set7 = set7 + 1
        elif card["set_id"] == "8":
            set8 = set8 + 1

        if card["description"] != "":
            deskripzioa = deskripzioa + 1

        if card["description"] == "" and card["power_id"] != "0":
            print(card["card_id"])
    print("normala: %s"%normala)
    print("berezia: %s"%berezia)
    print("sua: %s"%sua)
    print("ura: %s"%ura)
    print("elurra: %s"%elurra)
    print("gorria: %s"%gorria)
    print("urdina: %s"%urdina)
    print("horia: %s"%horia)
    print("berdea: %s"%berdea)
    print("laranja: %s"%laranja)
    print("morea: %s"%morea)
    print("set1: %s"%set1)
    print("set2: %s"%set2)
    print("set3: %s"%set3)
    print("set4: %s"%set4)
    print("set5: %s"%set5)
    print("set6: %s"%set6)
    print("set7: %s"%set7)
    print("set8: %s"%set8)
    print("deskripzioa: %s"%deskripzioa)

def efektuaItzuli(card):
    if card["description"].lower() == "When this is scored, your card gets +2 for the next round".lower():
        return "Karta honekin irabaztean, zure hurrengo kartaren balioa +2 izango da.#hurrengo txandan <jokalaria>-ren karta +2 balioko du."
    elif card["description"].lower() == "When this is scored, your Opponent's card get -2 for the next round".lower():
        return "Karta honekin irabaztean, lehiakidearen hurrengo kartaren balioa -2 izango da.#hurrengo txandan <jokalaria>-ren karta -2 balioko du."
    elif card["description"].lower() == "When this card is played, lower values win ties the next round.".lower():
        return "Karta hau erabiltzean, hurrengo txandan balio txikiko karta irabaziko du.#"
    elif card["description"].lower() == "When this is scored, discard one Opponent's Fire card.".lower():
        return "Karta honekin irabaztean, lehiakidearen sua den karta bat ezabatu.#<jokalaria>-ren sua den karta bat ezabatuko da."
    elif card["description"].lower() == "When this is scored, discard one Opponent's Water card.".lower():
        return "Karta honekin irabaztean, lehiakidearen ura den karta bat ezabatu.#<jokalaria>-ren ura den karta bat ezabatuko da."
    elif card["description"].lower() == "When this is scored, discard one Opponent's Snowball card.".lower():
        return "Karta honekin irabaztean, lehiakidearen elurra den karta bat ezabatu.#<jokalaria>-ren elurra den karta bat ezabatuko da."
    elif card["description"].lower() == "When this is scored, discard one Opponent's Red card.".lower():
        return "Karta honekin irabaztean, lehiakidearen gorria den karta bat ezabatu.#<jokalaria>-ren gorria den karta bat ezabatuko da."
    elif card["description"].lower() == "When this is scored, discard one Opponent's Blue card.".lower():
        return "Karta honekin irabaztean, lehiakidearen urdina den karta bat ezabatu.#<jokalaria>-ren urdina den karta bat ezabatuko da."
    elif card["description"].lower() == "When this is scored, discard one Opponent's Yellow card.".lower():
        return "Karta honekin irabaztean, lehiakidearen horia den karta bat ezabatu.#<jokalaria>-ren horia den karta bat ezabatuko da."
    elif card["description"].lower() == "When this is scored, discard one Opponent's Green card.".lower():
        return "Karta honekin irabaztean, lehiakidearen berdea den karta bat ezabatu.#<jokalaria>-ren berdea den karta bat ezabatuko da."
    elif card["description"].lower() == "When this is scored, discard one Opponent's Orange card.".lower():
        return "Karta honekin irabaztean, lehiakidearen laranja den karta bat ezabatu.#<jokalaria>-ren laranja den karta bat ezabatuko da."
    elif card["description"].lower() == "When this is scored, discard one Opponent's Purple card.".lower():
        return "Karta honekin irabaztean, lehiakidearen morea den karta bat ezabatu.#<jokalaria>-ren morea den karta bat ezabatuko da."
    elif card["description"].lower() == "When this is scored, discard Opponent's all Red cards.".lower():
        return "Karta honekin irabaztean, lehiakidearen gorriak diren karta guztiak ezabatu.#<jokalaria>-ren gorriak diren karta guztiak ezabatuko dira."
    elif card["description"].lower() == "When this is scored, discard Opponent's all Blue cards.".lower():
        return "Karta honekin irabaztean, lehiakidearen urdinak diren karta guztiak ezabatu.#<jokalaria>-ren urdinak diren karta guztiak ezabatuko dira."
    elif card["description"].lower() == "When this is scored, discard Opponent's all Yellow cards.".lower():
        return "Karta honekin irabaztean, lehiakidearen horiak diren karta guztiak ezabatu.#<jokalaria>-ren horiak diren karta guztiak ezabatuko dira."
    elif card["description"].lower() == "When this is scored, discard Opponent's all Green cards.".lower():
        return "Karta honekin irabaztean, lehiakidearen berdeak diren karta guztiak ezabatu.#<jokalaria>-ren berdeak diren karta guztiak ezabatuko dira."
    elif card["description"].lower() == "When this is scored, discard Opponent's all Orange cards.".lower():
        return "Karta honekin irabaztean, lehiakidearen laranjak diren karta guztiak ezabatu.#<jokalaria>-ren laranjak diren karta guztiak ezabatuko dira."
    elif card["description"].lower() == "When this is scored, discard Opponent's all Purple cards.".lower():
        return "Karta honekin irabaztean, lehiakidearen moreak diren karta guztiak ezabatu.#<jokalaria>-ren moreak diren karta guztiak ezabatuko dira."
    elif card["description"].lower() == "When this is played, Fire cards become Snowball for this round.".lower():
        return "Karta hau erabiltzean, lehiakidearen karta sua izatetik elurra izatera pasatzen da.#"
    elif card["description"].lower() == "When this is played, Snowball cards become Water for this round.".lower():
        return "Karta hau erabiltzean, lehiakidearen karta elurra izatetik ura izatera pasatzen da.#"
    elif card["description"].lower() == "When this is played, Water cards become Fire for this round.".lower():
        return "Karta hau erabiltzean, lehiakidearen karta ura izatetik sua izatera pasatzen da.#"
    elif card["description"].lower() == "When this is scored, Fire cannot be played next round.".lower():
        return "Karta honekin irabaztean, hurrengo errondan lehiakidea ezingo du sua den karta bat erabili.#hurrengo txandan, <jokalaria> ezin izango du sua den karta erabili."
    elif card["description"].lower() == "When this is scored, Water cannot be played next round.".lower():
        return "Karta honekin irabaztean, hurrengo errondan lehiakidea ezingo du ura den karta bat erabili.#hurrengo txandan, <jokalaria> ezin izango du ura den karta erabili."
    elif card["description"].lower() == "When this is scored, Snow cannot be played next round.".lower():
        return "Karta honekin irabaztean, hurrengo errondan lehiakidea ezingo du elurra den karta bat erabili.#hurrengo txandan, <jokalaria> ezin izango du elurra den karta erabili."
    else:
        return ""
        
def kartakKonprobatu(card):
	print("E: ", end='')
	if card["element"] == "f":
		print("SUA", end='')
	elif(card["element"] == "s"):
		print("ELURRA", end='')
	elif(card["element"] == "w"):
		print("URA", end='')
	print(" B: ", end='')
	print(card["value"], end='')
	print(" K: ", end='')
	if(card["color"] == "r"):
		print("GORRIA", end='')
	elif(card["color"] == "b"):
		print("URDINA", end='')
	elif(card["color"] == "y"):
		print("HORIA", end='')
	elif(card["color"] == "g"):
		print("BERDEA", end='')
	elif(card["color"] == "o"):
		print("LARANJA", end='')
	elif(card["color"] == "p"):
		print("MOREA", end='')
	if(card["power_id"] != "0"):
		print(" Ef: ", end='')
		if(card["power_id"] == "1"):
			print("ZENBAKIALDAKETA", end='')
		elif(card["power_id"] == "2"):
			print("BIGEHITU", end='')
		elif(card["power_id"] == "3"):
			print("BIKENDU", end='')
		elif(card["power_id"] == "4"):
			print("KENDUELURRA", end='')
		elif(card["power_id"] == "5"):
			print("KENDUURA", end='')
		elif(card["power_id"] == "6"):
			print("KENDUSUA", end='')
		elif(card["power_id"] == "7"):
			print("KENDUGORRIBAT", end='')
		elif(card["power_id"] == "8"):
			print("KENDUURDINBAT", end='')
		elif(card["power_id"] == "9"):
			print("KENDUBERDEBAT", end='')
		elif(card["power_id"] == "10"):
			print("KENDUHORIBAT", end='')
		elif(card["power_id"] == "11"):
			print("KENDULARANJABAT", end='')
		elif(card["power_id"] == "12"):
			print("KENDUMOREBAT", end='')
		elif(card["power_id"] == "13"):
			print("ELURRABLOKEATU", end='')
		elif(card["power_id"] == "14"):
			print("SUABLOKEATU", end='')
		elif(card["power_id"] == "15"):
			print("URABLOKEATU", end='')
		elif(card["power_id"] == "16"):
			print("URATIKSURA", end='')
		elif(card["power_id"] == "17"):
			print("ELURRATIKURARA", end='')
		elif(card["power_id"] == "18"):
			print("SUTIKELURRARA", end='')
		elif(card["power_id"] == "19"):
			print("KENDUGORRIGUZTIAK", end='')
		elif(card["power_id"] == "20"):
			print("KENDUURDINGUZTIAK", end='')
		elif(card["power_id"] == "21"):
			print("KENDUHORIGUZTIAK", end='')
		elif(card["power_id"] == "22"):
			print("KENDUBERDEGUZTIAK", end='')
		elif(card["power_id"] == "23"):
			print("KENDULARANJAGUZTIAK", end='')
		elif(card["power_id"] == "24"):
			print("KENDUMOREGUZTIAK", end='')
		print(" D: ", end='')
		print(card["description"])
	else:
		print("")
idEguneratu()
#kartenKopuruak()

#for card in c_dict:
	#kartakKonprobatu(card)