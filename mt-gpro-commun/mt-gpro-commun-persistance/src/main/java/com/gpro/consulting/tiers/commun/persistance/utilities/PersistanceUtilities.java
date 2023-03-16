package com.gpro.consulting.tiers.commun.persistance.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.uploadFichier.IGestionnaireDocument;
import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.RoleValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.BoutiqueValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.CompteComptableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.DetailsPackageValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.DocumentArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.DocumentProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.FamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.FamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.GrosseurValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ImpressionProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MatiereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MetrageValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MoldsValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OperationArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OperationProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OptionArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OptionProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PackageValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RemiseValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SeuilArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SocieteValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SuperFamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.TypeArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.UniteArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.UtilsValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.BanqueValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.CategorieValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.CompteComptablePIValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.DeviseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.DocumentValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.FamilleValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.GroupeClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RegionValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RepresentantValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.SiteValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.TypeDocumentValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.TypeValue;
import com.gpro.consulting.tiers.commun.persistance.baseinfo.entity.BaseInfoEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ArticleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.BoutiqueEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.CompteComptableEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.CouleurEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.DetailsPackageEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.DocumentArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.DocumentProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.FamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.FamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.GrosseurEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ImpressionProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.MatiereArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.MetrageEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.MoldsEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.OperationArticleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.OperationProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.OptionArticleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.OptionProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.PackageEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.PrixClientEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ProduitSerialisableEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.RemiseEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.SeuilArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.SocieteEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.SousFamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.SousFamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.SuperFamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.TypeArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.UniteArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.UtilsEntite;
import com.gpro.consulting.tiers.commun.persistance.login.entity.RoleEntity;
import com.gpro.consulting.tiers.commun.persistance.login.entity.UserEntity;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.BanqueEntite;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.CategorieEntite;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.CompteComptablePIEntity;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.DeviseEntite;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.DocumentEntite;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.FamilleEntite;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.GroupeClientEntite;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.PartieInteresseEntite;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.RegionEntite;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.RepresentantEntite;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.SiteEntite;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.TypeDocumentEntite;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.TypeEntite;

/**
 * Classe Utilitaire permettant de convertir des objets valeur en entité et des
 * objets entité en objets valeur
 * 
 * @author DELL
 *
 */

@Component
public class PersistanceUtilities {

	private IGestionnaireDocument vGestionnaireDocument;
	
	/**
	 * Instanciation du gestionnaire de persistance
	 */
//	private static PersistanceUtilities instance = new PersistanceUtilities();

	/**
	 * Méthode permettant l'accés au Gestionnaire de persistance
	 * 
	 * @return
	 */
//	public static PersistanceUtilities getInstance() {
//		return instance;
//	}
	
	
	
	
	public static BaseInfoValue toBaseInfoValue(BaseInfoEntity entity) {

		BaseInfoValue dto = new BaseInfoValue();

		dto.setId(entity.getId());
		dto.setDesignation(entity.getDesignation());
		dto.setValeur(entity.getValeur());
		dto.setUnite(entity.getUnite());
		dto.setLogo(entity.getLogo());
		dto.setActif(entity.isActif());
        dto.setCoutMinute(entity.getCoutMinute());
        
        
        dto.setAdresse(entity.getAdresse());
        dto.setVille(entity.getVille());
        dto.setCodeTVA(entity.getCodeTVA());
        dto.setTelephoneFix(entity.getTelephoneFix());
        dto.setTelephoneMoblie(entity.getTelephoneMoblie());
        dto.setEmail(entity.getEmail());
        dto.setGuichetClient(entity.getGuichetClient());
        dto.setGuichetFournisseur(entity.getGuichetFournisseur());
        
        dto.setPrefixFacture(entity.getPrefixFacture());
        dto.setPrefixBonLivraison(entity.getPrefixBonLivraison());
        dto.setPrefixAvoir(entity.getPrefixAvoir());
        
        dto.setMatriculeFiscal(entity.getMatriculeFiscal());
        
        dto.setPrefixClient(entity.getPrefixClient());
        dto.setPrefixFournisseur(entity.getPrefixFournisseur());
        
        
        
        dto.setDisableFinance(entity.isDisableFinance());
        dto.setDisableAchat(entity.isDisableAchat());
        dto.setDisableVente(entity.isDisableVente());
        dto.setDisableParametrage(entity.isDisableParametrage());
        
        dto.setHasStock(entity.isHasStock());
        dto.setHasAchat(entity.isHasAchat());
        dto.setHasCaisse(entity.isHasCaisse());
        
        
        dto.setArchiveDirectory(entity.getArchiveDirectory());
        dto.setLogoPNG(entity.getLogoPNG());
        
        dto.setExcelDirectory(entity.getExcelDirectory());
        dto.setBlackMode(entity.isBlackMode());
        
        dto.setDateDemarrage(entity.getDateDemarrage());
        
        
        dto.setTva19(entity.getTva19());
        dto.setFodec(entity.getFodec());
		
        dto.setRefCommandeFactureObligatoire(entity.getRefCommandeFactureObligatoire());
		
        dto.setIdentifiantFactureObligatoire(entity.getIdentifiantFactureObligatoire());
   
        dto.setContrainteModificationBl(entity.getContrainteModificationBl());
        dto.setDetails(entity.getDetails());
        dto.setSpecial(entity.getSpecial());
        dto.setReglementAvoir(entity.getReglementAvoir());
        dto.setBesoin(entity.getBesoin());
        dto.setReglementBl(entity.getReglementBl());

        
		
        
		return dto;
	}

	public static BaseInfoEntity toBaseInfoEntity(BaseInfoValue dto) {

		BaseInfoEntity entity = new BaseInfoEntity();

		entity.setId(dto.getId());
		entity.setDesignation(dto.getDesignation());
		entity.setValeur(dto.getValeur());
		entity.setUnite(dto.getUnite());
		entity.setLogo(dto.getLogo());
		entity.setActif(dto.isActif());
		
		entity.setAdresse(dto.getAdresse());
		entity.setVille(dto.getVille());
		entity.setCodeTVA(dto.getCodeTVA());
		entity.setTelephoneFix(dto.getTelephoneFix());
		entity.setTelephoneMoblie(dto.getTelephoneMoblie());
		entity.setEmail(dto.getEmail());
		entity.setGuichetClient(dto.getGuichetClient());
		entity.setGuichetFournisseur(dto.getGuichetFournisseur());
		
		entity.setPrefixFacture(dto.getPrefixFacture());
		entity.setPrefixBonLivraison(dto.getPrefixBonLivraison());
		entity.setPrefixAvoir(dto.getPrefixAvoir());
		
		entity.setMatriculeFiscal(dto.getMatriculeFiscal());
		
		
		entity.setPrefixClient(dto.getPrefixClient());
		entity.setPrefixFournisseur(dto.getPrefixFournisseur());
		
		
		entity.setDisableFinance(dto.isDisableFinance());
		entity.setDisableAchat(dto.isDisableAchat());
		entity.setDisableVente(dto.isDisableVente());
		entity.setDisableParametrage(dto.isDisableParametrage());
		
		
		entity.setHasStock(dto.isHasStock());
		entity.setHasAchat(dto.isHasAchat());
		entity.setHasCaisse(dto.isHasCaisse());
		
		
		entity.setArchiveDirectory(dto.getArchiveDirectory());
		
		entity.setLogoPNG(dto.getLogoPNG());
		
		entity.setExcelDirectory(dto.getExcelDirectory());
		entity.setBlackMode(dto.isBlackMode());
		
		entity.setDateDemarrage(dto.getDateDemarrage());
	
		entity.setTva19(dto.getTva19());
		entity.setFodec(dto.getFodec());
		
		
		entity.setRefCommandeFactureObligatoire(dto.getRefCommandeFactureObligatoire());
		
		entity.setIdentifiantFactureObligatoire(dto.getIdentifiantFactureObligatoire());
		
		entity.setContrainteModificationBl(dto.getContrainteModificationBl());
		entity.setDetails(dto.getDetails());
		entity.setSpecial(dto.getSpecial());
		entity.setReglementAvoir(dto.getReglementAvoir());
		entity.setBesoin(dto.getBesoin());
		entity.setReglementBl(dto.getReglementBl());
		
		return entity;
	}
	
	

	/**
	 * Converstion Partie Interessée value en entity
	 **/
	/**
	 * Converstion Partie Interessée value en entity
	 **/
	public PartieInteresseEntite toPartieInteresseEntity(
			PartieInteresseValue pPartieInteresseValue) {

		PartieInteresseEntite vPartieInteresseEntite = new PartieInteresseEntite();

		if (pPartieInteresseValue.getId() != null) {
			vPartieInteresseEntite.setId(pPartieInteresseValue.getId());
		}
		/** The ref. */
		vPartieInteresseEntite.setReference(pPartieInteresseValue
				.getReference());
		/** raison sociale. */
		vPartieInteresseEntite.setRaisonSociale(pPartieInteresseValue
				.getRaisonSociale());
		/** Abréviation. */
		vPartieInteresseEntite.setAbreviation(pPartieInteresseValue
				.getAbreviation());
		/** devise. */
		vPartieInteresseEntite.setDevise(pPartieInteresseValue.getDevise());
		/** Activité */
		vPartieInteresseEntite.setActivite(pPartieInteresseValue.getActivite());
		/** Observation. */
		vPartieInteresseEntite.setObservation(pPartieInteresseValue
				.getObservation());
		/** date introduction */
		vPartieInteresseEntite.setDateIntroduction(pPartieInteresseValue
				.getDateIntroduction());
		/** Matricule fiscale. */
		vPartieInteresseEntite.setMatrFiscal(pPartieInteresseValue
				.getMatriculeFiscal());
		/** regime commercial. */
		vPartieInteresseEntite.setRegimeCommercial(pPartieInteresseValue
				.getRegimeCommercial());
		/** code douane. */
		vPartieInteresseEntite.setCodeDouane(pPartieInteresseValue
				.getCodeDouane());
		/** adresse. */
		vPartieInteresseEntite.setAdresse(pPartieInteresseValue.getAdresse());
		/** email */
		vPartieInteresseEntite.setEmail(pPartieInteresseValue.getEmail());
		/** telephone */
		vPartieInteresseEntite.setTelephone(pPartieInteresseValue
				.getTelephone());
		/** fax */
		vPartieInteresseEntite.setFax(pPartieInteresseValue.getFax());
		/** actif */
		vPartieInteresseEntite.setActif(pPartieInteresseValue.isActif());
		/** famille */
		vPartieInteresseEntite.setFamillePartieInteressee(pPartieInteresseValue
				.getFamillePartieInteressee());
		/** type */
		vPartieInteresseEntite.setTypePartieInteressee(pPartieInteresseValue
				.getTypePartieInteressee());
		/** site */
		vPartieInteresseEntite.setSitePartieInteressee(pPartieInteresseValue
				.getSitePartieInteressee());
		/** categorie */
		vPartieInteresseEntite
				.setCategoriePartieInteressee(pPartieInteresseValue
						.getCategoriePartieInteressee());
		/** region */
		vPartieInteresseEntite.setRegionId(pPartieInteresseValue
				.getRegionId());
		
		vPartieInteresseEntite.setGroupeClientId(pPartieInteresseValue.getGroupeClientId());
		
		vPartieInteresseEntite.setPassager(pPartieInteresseValue.getPassager());
		
		vPartieInteresseEntite.setBoutiqueId(pPartieInteresseValue.getBoutiqueId());
		
		vPartieInteresseEntite.setTelephoneMobile(pPartieInteresseValue.getTelephoneMobile());
		
		vPartieInteresseEntite.setPayementTerme(pPartieInteresseValue.getPayementTerme());
		
		vPartieInteresseEntite.setCompteComptablePartieInteressee(pPartieInteresseValue.getCompteComptablePartieInteressee());
		
		
		vPartieInteresseEntite.setBanqueId(pPartieInteresseValue.getBanqueId());
		
		vPartieInteresseEntite.setCodeBancaire(pPartieInteresseValue.getCodeBancaire());
	
		
		vPartieInteresseEntite.setEmail2(pPartieInteresseValue.getEmail2());
		
		vPartieInteresseEntite.setCodeProduit(pPartieInteresseValue.getCodeProduit());
		
		
		vPartieInteresseEntite.setNature(pPartieInteresseValue.getNature());
		
		
		vPartieInteresseEntite.setDeviseId(pPartieInteresseValue.getDeviseId());
		
		
		
		// Liste Document
		if (pPartieInteresseValue.getDocuments() != null) {

			Set<DocumentEntite> vListeDocuments = new HashSet<DocumentEntite>();

			for (DocumentValue vDocumentValue : pPartieInteresseValue
					.getDocuments()) {
				DocumentEntite vDe = this.toEntite(vDocumentValue);
				vDe.setPartieInteresse(vPartieInteresseEntite);
				vListeDocuments.add(vDe);
			}

			vPartieInteresseEntite.setDocumentEntites(vListeDocuments);

		}
		// Liste Représentants

		if (pPartieInteresseValue.getRepresentants() != null) {

			Set<RepresentantEntite> vListeRepresentants = new HashSet<RepresentantEntite>();

			for (RepresentantValue vRepresentantValue : pPartieInteresseValue
					.getRepresentants()) {
				RepresentantEntite vRe = toEntite(vRepresentantValue);
				vRe.setPartieInteresse(vPartieInteresseEntite);
				vListeRepresentants.add(vRe);
			}

			vPartieInteresseEntite.setRepresentantEntites(vListeRepresentants);

		}

		return vPartieInteresseEntite;
	}

	/**
	 * Converstion Partie Interessée entity en value
	 **/
	public PartieInteresseValue toPartieInteresseValue(
			PartieInteresseEntite pPartieInteresseEntity) {

		PartieInteresseValue vPartieInteresseValue = new PartieInteresseValue();

		vPartieInteresseValue.setId(pPartieInteresseEntity.getId());

		/** The ref. */
		vPartieInteresseValue.setReference(pPartieInteresseEntity
				.getReference());
		/** raison sociale. */
		vPartieInteresseValue.setRaisonSociale(pPartieInteresseEntity
				.getRaisonSociale());
		/** Abréviation. */
		vPartieInteresseValue.setAbreviation(pPartieInteresseEntity
				.getAbreviation());
		/** devise. */
		vPartieInteresseValue.setDevise(pPartieInteresseEntity.getDevise());
		/** Activité */
		vPartieInteresseValue.setActivite(pPartieInteresseEntity.getActivite());
		/** Observation. */
		vPartieInteresseValue.setObservation(pPartieInteresseEntity
				.getObservation());
		/** date introduction */
		vPartieInteresseValue.setDateIntroduction(pPartieInteresseEntity
				.getDateIntroduction());
		/** Matricule fiscale. */
		vPartieInteresseValue.setMatriculeFiscal(pPartieInteresseEntity
				.getMatrFiscal());
		/** regime commercial. */
		vPartieInteresseValue.setRegimeCommercial(pPartieInteresseEntity
				.getRegimeCommercial());
		/** code douane. */
		vPartieInteresseValue.setCodeDouane(pPartieInteresseEntity
				.getCodeDouane());
		/** adresse. */
		vPartieInteresseValue.setAdresse(pPartieInteresseEntity.getAdresse());
		/** email */
		vPartieInteresseValue.setEmail(pPartieInteresseEntity.getEmail());
		/** telephone */
		vPartieInteresseValue.setTelephone(pPartieInteresseEntity
				.getTelephone());
		/** fax */
		vPartieInteresseValue.setFax(pPartieInteresseEntity.getFax());
		/** actif */
		vPartieInteresseValue.setActif(pPartieInteresseEntity.isActif());
		/** famille */
		vPartieInteresseValue.setFamillePartieInteressee(pPartieInteresseEntity
				.getFamillePartieInteressee());
		/** type */
		vPartieInteresseValue.setTypePartieInteressee(pPartieInteresseEntity
				.getTypePartieInteressee());
		/** site */
		vPartieInteresseValue.setSitePartieInteressee(pPartieInteresseEntity
				.getSitePartieInteressee());
		/** categorie */
		vPartieInteresseValue
				.setCategoriePartieInteressee(pPartieInteresseEntity
						.getCategoriePartieInteressee());
		/** region */
		vPartieInteresseValue
				.setRegionId(pPartieInteresseEntity
						.getRegionId());
		
		/** groupe client */
		
		vPartieInteresseValue.setGroupeClientId(pPartieInteresseEntity.getGroupeClientId());
		
		/** Passager  */
		vPartieInteresseValue.setPassager(pPartieInteresseEntity.getPassager());
		
		vPartieInteresseValue.setBoutiqueId(pPartieInteresseEntity.getBoutiqueId());
		
		vPartieInteresseValue.setTelephoneMobile(pPartieInteresseEntity.getTelephoneMobile());
		
		vPartieInteresseValue.setPayementTerme(pPartieInteresseEntity.getPayementTerme());
		
		vPartieInteresseValue.setCompteComptablePartieInteressee(pPartieInteresseEntity.getCompteComptablePartieInteressee());
		
		
		vPartieInteresseValue.setBanqueId(pPartieInteresseEntity.getBanqueId());
		
		
		vPartieInteresseValue.setCodeBancaire(pPartieInteresseEntity.getCodeBancaire());
	
		
		vPartieInteresseValue.setEmail2(pPartieInteresseEntity.getEmail2());
		
		vPartieInteresseValue.setCodeProduit(pPartieInteresseEntity.getCodeProduit());
		
		vPartieInteresseValue.setNature(pPartieInteresseEntity.getNature());
		
		vPartieInteresseValue.setDeviseId(pPartieInteresseEntity.getDeviseId());
		
		// Liste Document
		if (pPartieInteresseEntity.getDocumentEntites() != null) {

			Set<DocumentValue> vListeDocuments = new HashSet<DocumentValue>();

			for (DocumentEntite vDocumentValue : pPartieInteresseEntity
					.getDocumentEntites()) {
				DocumentValue vDv = toValue(vDocumentValue);
				vListeDocuments.add(vDv);
			}

			vPartieInteresseValue.setDocuments(vListeDocuments);
		}
		// Liste Représentants

		if (pPartieInteresseEntity.getRepresentantEntites() != null) {

			Set<RepresentantValue> vListeRepresentants = new HashSet<RepresentantValue>();

			for (RepresentantEntite vRepresentantValue : pPartieInteresseEntity
					.getRepresentantEntites()) {
				RepresentantValue vRv = toValue(vRepresentantValue);

				vListeRepresentants.add(vRv);
			}

			vPartieInteresseValue.setRepresentants(vListeRepresentants);

		}

		return vPartieInteresseValue;
	}
	
	
	
	
	/**
	 * Converstion Boutique value en entity
	 **/
	/**
	 * ConverstionBoutique value en entity
	 **/
	public static BoutiqueEntite toBoutiqueEntity(
			BoutiqueValue pBoutiqueValue) {

		BoutiqueEntite vBoutiqueEntite = new BoutiqueEntite();

		if (pBoutiqueValue.getId() != null) {
			vBoutiqueEntite.setId(pBoutiqueValue.getId());
		}
		/** The ref. */
		vBoutiqueEntite.setReference(pBoutiqueValue
				.getReference());
		/** raison sociale. */
		vBoutiqueEntite.setRaisonSociale(pBoutiqueValue
				.getRaisonSociale());
		/** Abréviation. */
		vBoutiqueEntite.setAbreviation(pBoutiqueValue
				.getAbreviation());
		/** devise. */
		vBoutiqueEntite.setDevise(pBoutiqueValue.getDevise());
		/** Activité */
		vBoutiqueEntite.setActivite(pBoutiqueValue.getActivite());
		/** Observation. */
		vBoutiqueEntite.setObservation(pBoutiqueValue
				.getObservation());
		/** date introduction */
		vBoutiqueEntite.setDateIntroduction(pBoutiqueValue
				.getDateIntroduction());
		/** Matricule fiscale. */
		vBoutiqueEntite.setMatrFiscal(pBoutiqueValue
				.getMatriculeFiscal());
		/** regime commercial. */
		vBoutiqueEntite.setRegimeCommercial(pBoutiqueValue
				.getRegimeCommercial());
		/** code douane. */
		vBoutiqueEntite.setCodeDouane(pBoutiqueValue
				.getCodeDouane());
		/** adresse. */
		vBoutiqueEntite.setAdresse(pBoutiqueValue.getAdresse());
		/** email */
		vBoutiqueEntite.setEmail(pBoutiqueValue.getEmail());
		/** telephone */
		vBoutiqueEntite.setTelephone(pBoutiqueValue
				.getTelephone());
		/** fax */
		vBoutiqueEntite.setFax(pBoutiqueValue.getFax());
		/** actif */
		vBoutiqueEntite.setActif(pBoutiqueValue.getActif());
		
		
		
		/** region */
		vBoutiqueEntite.setRegionId(pBoutiqueValue
				.getRegionId());
		

		vBoutiqueEntite.setObjectif(pBoutiqueValue.getObjectif());
		
		
		
		
		
		
		if(pBoutiqueValue.getSocieteId() != null) {
			
			SocieteEntite societe = new SocieteEntite();
			
			societe.setId(pBoutiqueValue.getSocieteId());
			
			vBoutiqueEntite.setSociete(societe);
			
		}



		return vBoutiqueEntite;
	}

	/**
	 * Converstion Boutique entity en value
	 **/
	public static BoutiqueValue toBoutiqueValue(
			BoutiqueEntite pBoutiqueEntity) {

		BoutiqueValue vBoutiqueValue = new BoutiqueValue();

		vBoutiqueValue.setId(pBoutiqueEntity.getId());

		/** The ref. */
		vBoutiqueValue.setReference(pBoutiqueEntity
				.getReference());
		/** raison sociale. */
		vBoutiqueValue.setRaisonSociale(pBoutiqueEntity
				.getRaisonSociale());
		/** Abréviation. */
		vBoutiqueValue.setAbreviation(pBoutiqueEntity
				.getAbreviation());
		/** devise. */
		vBoutiqueValue.setDevise(pBoutiqueEntity.getDevise());
		/** Activité */
		vBoutiqueValue.setActivite(pBoutiqueEntity.getActivite());
		/** Observation. */
		vBoutiqueValue.setObservation(pBoutiqueEntity
				.getObservation());
		/** date introduction */
		vBoutiqueValue.setDateIntroduction(pBoutiqueEntity
				.getDateIntroduction());
		/** Matricule fiscale. */
		vBoutiqueValue.setMatriculeFiscal(pBoutiqueEntity
				.getMatrFiscal());
		/** regime commercial. */
		vBoutiqueValue.setRegimeCommercial(pBoutiqueEntity
				.getRegimeCommercial());
		/** code douane. */
		vBoutiqueValue.setCodeDouane(pBoutiqueEntity
				.getCodeDouane());
		/** adresse. */
		vBoutiqueValue.setAdresse(pBoutiqueEntity.getAdresse());
		/** email */
		vBoutiqueValue.setEmail(pBoutiqueEntity.getEmail());
		/** telephone */
		vBoutiqueValue.setTelephone(pBoutiqueEntity
				.getTelephone());
		/** fax */
		vBoutiqueValue.setFax(pBoutiqueEntity.getFax());
		/** actif */
		vBoutiqueValue.setActif(pBoutiqueEntity.getActif());
	

		/** region */
		vBoutiqueValue
				.setRegionId(pBoutiqueEntity
						.getRegionId());
		vBoutiqueValue.setObjectif(pBoutiqueEntity.getObjectif());
		
		
		
		
		
		
		if(pBoutiqueEntity.getSociete() != null) {
			vBoutiqueValue.setSocieteId(pBoutiqueEntity.getSociete().getId());
		}
	

		return vBoutiqueValue;
	}

	
	
	
	/** Remise Value **/
	
	

	/**
	 * Converstion Remise value en entity
	 **/
	/**
	 * Converstion Remise value en entity
	 **/
	public static RemiseEntite toRemiseEntity(
			RemiseValue remiseValue) {

		RemiseEntite vRemiseEntite = new RemiseEntite();

		if (remiseValue.getId() != null) {
			vRemiseEntite.setId(remiseValue.getId());
		}
		
		vRemiseEntite.setRemise(remiseValue.getRemise());
		vRemiseEntite.setBoutiqueId(remiseValue.getBoutiqueId());
		vRemiseEntite.setDateDebut(remiseValue.getDateDebut());
		vRemiseEntite.setDateFin(remiseValue.getDateFin());
		vRemiseEntite.setProduitId(remiseValue.getProduitId());


		return vRemiseEntite;
	}

	/**
	 * Converstion Remise entity en value
	 **/
	public static RemiseValue toRemiseValue(
			RemiseEntite vRemiseEntite) {

		RemiseValue remiseValue = new RemiseValue();

		remiseValue.setId(vRemiseEntite.getId());
		remiseValue.setRemise(vRemiseEntite.getRemise());
		remiseValue.setBoutiqueId(vRemiseEntite.getBoutiqueId());
		remiseValue.setDateDebut(vRemiseEntite.getDateDebut());
		remiseValue.setDateFin(vRemiseEntite.getDateFin());
		remiseValue.setProduitId(vRemiseEntite.getProduitId());
	

		return remiseValue;
	}

	
	
	
	

	/**
	 * @param vRepresentantValue
	 * @return
	 */
	public static RepresentantEntite toEntite(
			RepresentantValue pRepresentantValue) {
		RepresentantEntite vRepresentantEntite = new RepresentantEntite();

		/** The id. */
		if (pRepresentantValue.getId() != null) {
			vRepresentantEntite.setId(pRepresentantValue.getId());
		}
		/** The nom. */
		vRepresentantEntite.setNom(pRepresentantValue.getNom());
		/** The fonction. */
		vRepresentantEntite.setFonction(pRepresentantValue.getFonction());
		/** The email. */
		vRepresentantEntite.setEmail(pRepresentantValue.getEmail());
		/** The telephone. */
		vRepresentantEntite.setTelephone(pRepresentantValue.getTelephone());
		/** The fax. */
		vRepresentantEntite.setFax(pRepresentantValue.getFax());

		return vRepresentantEntite;
	}

	/**
	 * Converstion entité à value du Representant
	 * 
	 * @param
	 * @return
	 */
	public static RepresentantValue toValue(
			RepresentantEntite pRepresentantEntite) {
		RepresentantValue vRepresentantValue = new RepresentantValue();

		/** The id. */

		vRepresentantValue.setId(pRepresentantEntite.getId());

		/** The nom. */
		vRepresentantValue.setNom(pRepresentantEntite.getNom());
		/** The fonction. */
		vRepresentantValue.setFonction(pRepresentantEntite.getFonction());
		/** The email. */
		vRepresentantValue.setEmail(pRepresentantEntite.getEmail());
		/** The telephone. */
		vRepresentantValue.setTelephone(pRepresentantEntite.getTelephone());
		/** The fax. */
		vRepresentantValue.setFax(pRepresentantEntite.getFax());

		return vRepresentantValue;
	}

	/**
	 * Converstion entité à value du Document
	 * 
	 * @param
	 * @return
	 */
	public DocumentEntite toEntite(DocumentValue pDocumentValue) {
		DocumentEntite vDocumentEntite = new DocumentEntite();

		/** Persistance niveau base de données */
		if (pDocumentValue.getId() != null) {
			vDocumentEntite.setId(pDocumentValue.getId());
		}
		vDocumentEntite.setUuidDocument(pDocumentValue.getvUUIDDocument());

		vDocumentEntite.setPath(pDocumentValue.getPath());
		vDocumentEntite.setTypeDocumentEntite(pDocumentValue.getTypeDocument());

		return vDocumentEntite;

	}

	/**
	 * Converstion value à entité du Document
	 * 
	 * @param
	 * @return
	 */
	public DocumentValue toValue(DocumentEntite pDocumentEntite) {
		DocumentValue vDocumentValue = new DocumentValue();

		if (pDocumentEntite.getId() != null) {
			vDocumentValue.setId(pDocumentEntite.getId());
		}
		/** Ajout du mapping de uuid */
		vDocumentValue.setvUUIDDocument(pDocumentEntite.getUuidDocument());
		vDocumentValue.setPath(pDocumentEntite.getPath());
		vDocumentValue.setTypeDocument(pDocumentEntite.getTypeDocumentEntite());

		return vDocumentValue;

	}

	/*** site partie int **/

	public static SiteEntite toEntite(SiteValue pSiteValue) {
		SiteEntite vSiteEntite = new SiteEntite();
		if (pSiteValue.getId() != null) {
			vSiteEntite.setId(pSiteValue.getId());
		}
		vSiteEntite.setNom(pSiteValue.getNom());
		return vSiteEntite;
	}

	public static SiteValue toValue(SiteEntite pSiteEntite) {
		SiteValue vSiteValue = new SiteValue();
		vSiteValue.setId(pSiteEntite.getId());
		vSiteValue.setNom(pSiteEntite.getNom());
		return vSiteValue;
	}

	/*** type document partie int ***/
	public static TypeDocumentValue toValue(
			TypeDocumentEntite pTypeDocumentEntite) {
		TypeDocumentValue vTypeDocumentValue = new TypeDocumentValue();
		vTypeDocumentValue.setId(pTypeDocumentEntite.getId());
		vTypeDocumentValue.setDesignation(pTypeDocumentEntite.getDesignation());
		vTypeDocumentValue.setModule(pTypeDocumentEntite.getModule());
		return vTypeDocumentValue;
	}

	public static TypeDocumentEntite toEntity(
			TypeDocumentValue pTypeDocumentValue) {
		TypeDocumentEntite vTypeDocumentEntite = new TypeDocumentEntite();
		if (pTypeDocumentValue.getId() != null) {
			vTypeDocumentEntite.setId(pTypeDocumentValue.getId());
		}
		vTypeDocumentEntite.setDesignation(pTypeDocumentValue.getDesignation());
		vTypeDocumentEntite.setModule(pTypeDocumentValue.getModule());
		
		return vTypeDocumentEntite;

	}

	/**
	 * Méthode permettant de convertir un objet valeur en entité.
	 */
	public static CategorieEntite toEntity(CategorieValue categorieValue) {
		CategorieEntite categorieEntite = new CategorieEntite();
		if (categorieValue.getId() != null) {
			categorieEntite.setId(categorieValue.getId());
		}
		categorieEntite.setDesignation(categorieValue.getDesignation());

		return categorieEntite;
	}

	/** Converstion CathegoriePartieINT entite en CathegoriePartieINT value **/
	public static CategorieValue toValue(CategorieEntite categorieEntite) {
		CategorieValue categorieValue = new CategorieValue();

		categorieValue.setId(categorieEntite.getId());
		categorieValue.setDesignation(categorieEntite.getDesignation());

		return categorieValue;
	}

	

	/**
	 * Méthode  banque entite
	 */
	public static BanqueEntite toEntity(BanqueValue categorieValue) {
		BanqueEntite categorieEntite = new BanqueEntite();
		if (categorieValue.getId() != null) {
			categorieEntite.setId(categorieValue.getId());
		}
		
		categorieEntite.setCode(categorieValue.getCode());
		categorieEntite.setAbreviation(categorieValue.getAbreviation());
		categorieEntite.setDesignation(categorieValue.getDesignation());
		
		categorieEntite.setSociete(categorieValue.getSociete());
		
	

		return categorieEntite;
	}

	/** Converstion CathegoriePartieINT entite en CathegoriePartieINT value **/
	public static BanqueValue toValue(BanqueEntite categorieEntite) {
		BanqueValue categorieValue = new BanqueValue();

		categorieValue.setId(categorieEntite.getId());
		
		categorieValue.setCode(categorieEntite.getCode());
		categorieValue.setAbreviation(categorieEntite.getAbreviation());
		categorieValue.setDesignation(categorieEntite.getDesignation());
		
		categorieValue.setSociete(categorieEntite.getSociete());

		return categorieValue;
	}
	/**************************** value to entite famille PI *********************************/

	/** Converstion Famille value en Famille entite **/
	public static FamilleEntite toFamilleEntity(FamilleValue familleValue) {
		FamilleEntite familleEntite = new FamilleEntite();
		if (familleValue.getId() != null) {
			familleEntite.setId(familleValue.getId());
			familleEntite.setAchat(familleValue.isAchat());
			familleEntite.setDesignation(familleValue.getDesignation());
			familleEntite.setVente(familleValue.isVente());
		}
		return familleEntite;
	}

	/**** listValue categorie PI to liste entity ******/
	public static List<CategorieValue> tolistCategoriePartieIntereseValues(
			List<CategorieEntite> pCategorieEntites) {
		List<CategorieValue> categorieValues = new ArrayList<CategorieValue>();
		for (CategorieEntite categorieEntite : pCategorieEntites) {
			categorieValues.add(PersistanceUtilities.toValue(categorieEntite));
		}
		return categorieValues;
	}

	public static FamilleValue toFamilleValue(FamilleEntite familleEntite) {
		FamilleValue familleValue = new FamilleValue();
		familleValue.setId(familleEntite.getId());
		familleValue.setAchat(familleEntite.isAchat());
		familleValue.setDesignation(familleEntite.getDesignation());
		familleValue.setVente(familleEntite.isVente());

		return familleValue;
	}

	/** Converstion type value en type entite **/
	public static TypeEntite toTypePArtieInteresseEntity(TypeValue typeValue) {
		TypeEntite typeEntite = new TypeEntite();
		if (typeValue.getId() != null) {
			typeEntite.setId(typeValue.getId());
		}
		typeEntite.setDesignation(typeValue.getDesignation());

		return typeEntite;
	}

	/** Converstion type entite en type value **/

	public static TypeValue toTypePArtieInteresseValue(TypeEntite typeEntite) {
		TypeValue typeValue = new TypeValue();
		typeValue.setId(typeEntite.getId());
		typeValue.setDesignation(typeEntite.getDesignation());
		return typeValue;
	}

	/*************** list entitys to list values *****************/
	@SuppressWarnings("null")
	public static List<CategorieValue> tolistValues(
			List<CategorieEntite> pCategorieEntites) {
		List<CategorieValue> categorieValues = null;
		for (int i = 0; i < pCategorieEntites.size(); i++) {
			categorieValues.add(toValue(pCategorieEntites.get(i)));

		}
		return categorieValues;
	}


	  /**************************** value to entite EbMatiere *********************************/

	  /** Converstion MatiereArticleValue en MatiereArticleEntite **/
	  public static MatiereArticleEntite toEntity(MatiereArticleValue matiereValue) {
	    MatiereArticleEntite vEbMatiereEntite = new MatiereArticleEntite();
	    if (matiereValue.getId() != null) {
	      vEbMatiereEntite.setId(matiereValue.getId());
	    }
	    vEbMatiereEntite.setDesignation(matiereValue.getDesignation());
	    return vEbMatiereEntite;
	  }

	  /** Converstion MatiereArticleEntite entite en MatiereArticleValue **/
	  public static MatiereArticleValue toValue(MatiereArticleEntite matiereArticleEntite) {
	    MatiereArticleValue ebmatiereValue = new MatiereArticleValue();
	    ebmatiereValue.setId(matiereArticleEntite.getId());
	    ebmatiereValue.setDesignation(matiereArticleEntite.getDesignation());
	    return ebmatiereValue;
	  }

	  /** Converstion ListeMatiereArticleEntite en ListeMatiereArticleValue **/
	  public static List < MatiereArticleValue > tolistMatiereArticleValues(List < MatiereArticleEntite > pMatiereArticleEntites) {
	    List < MatiereArticleValue > MatiereArticleValues = new ArrayList < MatiereArticleValue >();
	    for (MatiereArticleEntite MatiereArticleEntite : pMatiereArticleEntites) {
	      MatiereArticleValues.add(PersistanceUtilities.toValue(MatiereArticleEntite));
	    }
	    return MatiereArticleValues;
	  }

	  /**************************** value to entite Metrage *********************************/

	  /** Converstion MetrageValue en MetrageEntite **/
	  public static MetrageEntite toEntity(MetrageValue metrageValue) {
	    MetrageEntite vMetrageEntite = new MetrageEntite();
	    if (metrageValue.getId() != null) {
	      vMetrageEntite.setId(metrageValue.getId());
	    }
	    vMetrageEntite.setDesignation(metrageValue.getDesignation());
	    return vMetrageEntite;
	  }

	  /** Converstion MatiereArticleEntite entite en MetrageValue **/
	  public static MetrageValue toValue(MetrageEntite pMetrageEntite) {
	    MetrageValue vMetrageValue = new MetrageValue();
	    vMetrageValue.setId(pMetrageEntite.getId());
	    vMetrageValue.setDesignation(pMetrageEntite.getDesignation());
	    return vMetrageValue;
	  }

	  /** Converstion ListeMetrageEntite en ListeMetrageValue **/
	  public static List < MetrageValue > tolistMetrageValues(List < MetrageEntite > pMetrageEntites) {
	    List < MetrageValue > MetrageValues = new ArrayList < MetrageValue >();
	    for (MetrageEntite MetrageEntite : pMetrageEntites) {
	      MetrageValues.add(PersistanceUtilities.toValue(MetrageEntite));
	    }
	    return MetrageValues;
	  }

	  /**************************** value to entite EbGrosseur *********************************/
	  /** Converstion GrosseurValue en GrosseurEntite **/
	  public static GrosseurEntite toEntity(GrosseurValue pGrosseurValue) {
	    GrosseurEntite vGrosseurEntite = new GrosseurEntite();
	    if (pGrosseurValue.getId() != null) {
	      vGrosseurEntite.setId(pGrosseurValue.getId());
	    }
	    vGrosseurEntite.setDesignation(pGrosseurValue.getDesignation());
	    return vGrosseurEntite;
	  }

	  /** Converstion GrosseurEntite entite en GrosseurValue **/
	  public static GrosseurValue toValue(GrosseurEntite pGrosseurEntite) {
	    GrosseurValue vGrosseurValue = new GrosseurValue();
	    vGrosseurValue.setId(pGrosseurEntite.getId());
	    vGrosseurValue.setDesignation(pGrosseurEntite.getDesignation());
	    return vGrosseurValue;
	  }

	  /**************************** value to entite EbCouleur *********************************/
	  /** Converstion CouleurValue en CouleurEntite **/
	  public static CouleurEntite toEntity(CouleurValue CouleurValue) {
	    CouleurEntite vEbCouleurEntite = new CouleurEntite();
	    if (CouleurValue.getId() != null) {
	      vEbCouleurEntite.setId(CouleurValue.getId());
	    }
	    vEbCouleurEntite.setDesignation(CouleurValue.getDesignation());
	    return vEbCouleurEntite;
	  }

	  /** Converstion CouleurEntite entite en CouleurValue **/
	  public static CouleurValue toValue(CouleurEntite CouleurEntite) {
	    CouleurValue ebCouleurValue = new CouleurValue();
	    ebCouleurValue.setId(CouleurEntite.getId());
	    ebCouleurValue.setDesignation(CouleurEntite.getDesignation());
	    return ebCouleurValue;
	  }

	  /** Converstion ListeCouleurEntite en ListeCouleurValue **/
	  public static List < CouleurValue > tolistCouleurValues(List < CouleurEntite > pCouleurEntites) {
	    List < CouleurValue > CouleurValues = new ArrayList < CouleurValue >();
	    for (CouleurEntite CouleurEntite : pCouleurEntites) {
	      CouleurValues.add(PersistanceUtilities.toValue(CouleurEntite));
	    }
	    return CouleurValues;
	  }

	/**************************** value to entite Devise *********************************/

	/** Converstion DeviseValue en DeviseEntite **/
	public static DeviseEntite toEntity(DeviseValue DeviseValue) {
		DeviseEntite vDeviseEntite = new DeviseEntite();
		if (DeviseValue.getId() != null) {
			vDeviseEntite.setId(DeviseValue.getId());
		}
		vDeviseEntite.setDesignation(DeviseValue.getDesignation());
		return vDeviseEntite;
	}

	/** Converstion DeviseEntite entite en DeviseValue **/
	public static DeviseValue toValue(DeviseEntite DeviseEntite) {
		DeviseValue DeviseValue = new DeviseValue();
		DeviseValue.setId(DeviseEntite.getId());
		DeviseValue.setDesignation(DeviseEntite.getDesignation());
		return DeviseValue;
	}

	/** Converstion ListeDeviseEntite en ListeDeviseValue **/
	public static List<DeviseValue> tolistDeviseValues(
			List<DeviseEntite> pDeviseEntites) {
		List<DeviseValue> DeviseValues = new ArrayList<DeviseValue>();
		for (DeviseEntite DeviseEntite : pDeviseEntites) {
			DeviseValues.add(PersistanceUtilities.toValue(DeviseEntite));
		}
		return DeviseValues;
	}

	/** SousFamilleArticleEntite to SousfamilleArticleValue **/
	public static SousFamilleProduitValue toValue(
			SousFamilleProduitEntity pSousFamilleProduitEntity) {
		SousFamilleProduitValue sousFamilleProduitValue = new SousFamilleProduitValue();
		sousFamilleProduitValue.setId(pSousFamilleProduitEntity.getId());
		sousFamilleProduitValue.setDesignation(pSousFamilleProduitEntity
				.getDesignation());
		sousFamilleProduitValue.setTva(pSousFamilleProduitEntity.getTva());
		sousFamilleProduitValue.setIdTaxe(pSousFamilleProduitEntity.getIdTaxe());
		sousFamilleProduitValue.setSerialisable(pSousFamilleProduitEntity.isSerialisable());
		if (pSousFamilleProduitEntity.getFamilleProduit() != null) {
			sousFamilleProduitValue
					.setFamilleProduitId(pSousFamilleProduitEntity
							.getFamilleProduit().getId());
		}
		return sousFamilleProduitValue;
	}

	/** SousFamilleArticleValue to SousfamilleArticleEntite **/
	public static SousFamilleProduitEntity toEntity(
			SousFamilleProduitValue pSousFamilleProduitValue,
			FamilleProduitEntity pFamilleProduit) {
		SousFamilleProduitEntity sousFamilleProduitEntity = new SousFamilleProduitEntity();
		if (pSousFamilleProduitValue.getId() != null) {
			sousFamilleProduitEntity.setId(pSousFamilleProduitValue.getId());
		}
		sousFamilleProduitEntity.setDesignation(pSousFamilleProduitValue
				.getDesignation());
		sousFamilleProduitEntity.setTva(pSousFamilleProduitValue.getTva());
		sousFamilleProduitEntity.setIdTaxe(pSousFamilleProduitValue.getIdTaxe());
		sousFamilleProduitEntity.setSerialisable(pSousFamilleProduitValue.isSerialisable());
		sousFamilleProduitEntity.setFamilleProduit(pFamilleProduit);
		return sousFamilleProduitEntity;
	}

	 /** Converstion ListeGrosseurEntite en ListeGrosseurValue **/
	  public static List < GrosseurValue > tolistGrosseurValues(List < GrosseurEntite > pGrosseurEntites) {
	    List < GrosseurValue > GrosseurValues = new ArrayList < GrosseurValue >();
	    for (GrosseurEntite GrosseurEntite : pGrosseurEntites) {
	      GrosseurValues.add(PersistanceUtilities.toValue(GrosseurEntite));
	    }
	    return GrosseurValues;
	  }

	  /**************************** FamilleArticlvalue to FamilleArticlEntite *********************************/

	  /** FamilleArticleEntite to familleArticleValue **/
	  public static FamilleArticleValue toValue(FamilleArticleEntity pFamilleArticleEntite) {
	    FamilleArticleValue familleArticleValue = new FamilleArticleValue();
	    familleArticleValue.setId(pFamilleArticleEntite.getId());
	    familleArticleValue.setDesignation(pFamilleArticleEntite.getDesignation());
	    if (pFamilleArticleEntite.getTypeArticle() != null) {
	      familleArticleValue.setIdTypeArticle(pFamilleArticleEntite.getTypeArticle().getId());
	    }
	    return familleArticleValue;

	  }

	  /** FamilleArticleValue to familleArticleEntite **/
	  public static FamilleArticleEntity toEntity(FamilleArticleValue pFamilleArticleValue, TypeArticleEntity pTypeArticle) {
	    FamilleArticleEntity familleArticleEntite = new FamilleArticleEntity();
	    if (pFamilleArticleValue.getId() != null) {
	      familleArticleEntite.setId(pFamilleArticleValue.getId());
	    }
	    familleArticleEntite.setDesignation(pFamilleArticleValue.getDesignation());
	    familleArticleEntite.setTypeArticle(pTypeArticle);
	    return familleArticleEntite;
	  }

	  /**** listValue famille article to liste entity ******/
	  public static List < FamilleArticleValue > tolistFamilleArticleValues(List < FamilleArticleEntity > pFamilleEntites) {
	    List < FamilleArticleValue > familleValues = new ArrayList < FamilleArticleValue >();
	    for (FamilleArticleEntity familleEntite : pFamilleEntites) {
	      familleValues.add(PersistanceUtilities.toValue(familleEntite));
	    }
	    return familleValues;
	  }

	  /** SeuilArticleEntite to SeuilArticleValue **/
	  public static SeuilArticleValue toValue(SeuilArticleEntity pSeuilArticleEntity) {
	    SeuilArticleValue seuilArticleValue = new SeuilArticleValue();
	    seuilArticleValue.setId(pSeuilArticleEntity.getId());
	    seuilArticleValue.setMaxSeuil(pSeuilArticleEntity.getMaxSeuil());
	    seuilArticleValue.setMinSeuil(pSeuilArticleEntity.getMinSeuil());
	    seuilArticleValue.setDateFin(pSeuilArticleEntity.getDateFin());
	    seuilArticleValue.setDateDebut(pSeuilArticleEntity.getDateDebut());
	    return seuilArticleValue;
	  }

	  /** SeuilArticleValue to SeuilArticleEntite **/
	  public static SeuilArticleEntity toEntity(SeuilArticleValue pSeuilArticleValue) {
	    SeuilArticleEntity seuilArticleEntity = new SeuilArticleEntity();
	    if (pSeuilArticleValue.getId() != null) {
	      seuilArticleEntity.setId(pSeuilArticleValue.getId());
	    }
	    seuilArticleEntity.setMaxSeuil(pSeuilArticleValue.getMaxSeuil());
	    seuilArticleEntity.setMinSeuil(pSeuilArticleValue.getMinSeuil());
	    seuilArticleEntity.setDateFin(pSeuilArticleValue.getDateFin());
	    seuilArticleEntity.setDateDebut(pSeuilArticleValue.getDateDebut());
	    return seuilArticleEntity;
	  }

	  /** sousfamilelArticleEntite to sousfamilleArticleValue **/
	  public static SousFamilleArticleValue toValue(SousFamilleArticleEntity pSousFamilleArticleEntity) {
	    SousFamilleArticleValue sousFamilleArticleValue = new SousFamilleArticleValue();
	    sousFamilleArticleValue.setId(pSousFamilleArticleEntity.getId());
	    if (pSousFamilleArticleEntity.getFamilleArticle() != null) {
	      sousFamilleArticleValue.setIdFamilleArticle(pSousFamilleArticleEntity.getFamilleArticle().getId());
	    }
	    sousFamilleArticleValue.setDesignation(pSousFamilleArticleEntity.getDesignation());
	    return sousFamilleArticleValue;
	  }

	  /** sousfamilleArticleValue to sousfamilleArticleEntite **/
	  public static SousFamilleArticleEntity toEntity(SousFamilleArticleValue pSousFamilleArticleValue,
	    FamilleArticleEntity pFamilleArticle) {
	    SousFamilleArticleEntity sousFamilleArticleEntity = new SousFamilleArticleEntity();
	    if (pSousFamilleArticleValue.getId() != null) {
	      sousFamilleArticleEntity.setId(pSousFamilleArticleValue.getId());
	    }
	    sousFamilleArticleEntity.setFamilleArticle(pFamilleArticle);
	    sousFamilleArticleEntity.setDesignation(pSousFamilleArticleValue.getDesignation());
	    return sousFamilleArticleEntity;
	  }

	  /**** listValue sous famille article article to liste entity ******/
	  public static List < SousFamilleArticleValue > tolistSousFamilleArticleValues(
	    List < SousFamilleArticleEntity > pSousFamilleEntites) {
	    List < SousFamilleArticleValue > sousFamilleArticleValues = new ArrayList < SousFamilleArticleValue >();
	    for (SousFamilleArticleEntity sousFamilleEntite : pSousFamilleEntites) {
	      sousFamilleArticleValues.add(PersistanceUtilities.toValue(sousFamilleEntite));
	    }
	    return sousFamilleArticleValues;
	  }

	  /** typelArticleEntite to typeArticleValue **/
	  public static TypeArticleValue toValue(TypeArticleEntity pTypeArticleEntity) {
	    TypeArticleValue typeArticleValue = new TypeArticleValue();
	    typeArticleValue.setId(pTypeArticleEntity.getId());
	    typeArticleValue.setDesignation(pTypeArticleEntity.getDesignation());
	    return typeArticleValue;
	  }

	  /** typeArticleValue to typeArticleEntite **/
	  public static TypeArticleEntity toEntity(TypeArticleValue pTypeArticleValue) {
	    TypeArticleEntity typeArticleEntity = new TypeArticleEntity();
	    if (pTypeArticleValue.getId() != null) {
	      typeArticleEntity.setId(pTypeArticleValue.getId());
	    }
	    typeArticleEntity.setDesignation(pTypeArticleValue.getDesignation());
	    return typeArticleEntity;
	  }

	  /**** listValue type article article to liste entity ******/
	  public static List < TypeArticleValue > tolistTypeArticleValues(List < TypeArticleEntity > pTypeArticleEntitys) {
	    List < TypeArticleValue > typeArticleValues = new ArrayList < TypeArticleValue >();
	    for (TypeArticleEntity typeArticleEntite : pTypeArticleEntitys) {
	      typeArticleValues.add(PersistanceUtilities.toValue(typeArticleEntite));
	    }
	    return typeArticleValues;
	  }

	  /** unitelArticleEntite to uniteArticleValue **/
	  public static UniteArticleValue toValue(UniteArticleEntity pUniteArticleEntity) {
	    UniteArticleValue uniteArticleValue = new UniteArticleValue();
	    uniteArticleValue.setId(pUniteArticleEntity.getId());
	    uniteArticleValue.setDesignation(pUniteArticleEntity.getDesignation());
	    return uniteArticleValue;
	  }

	  /** uniteArticleValue to uniteArticleEntite **/
	  public static UniteArticleEntity toEntity(UniteArticleValue pUniteArticleValue) {
	    UniteArticleEntity uniteArticleEntity = new UniteArticleEntity();
	    if (pUniteArticleValue.getId() != null) {
	      uniteArticleEntity.setId(pUniteArticleValue.getId());
	    }
	    uniteArticleEntity.setDesignation(pUniteArticleValue.getDesignation());
	    return uniteArticleEntity;
	  }
	  
	// ///Persistance Article

	  /**
	   * Converstion Article value en entity
	   **/
	  public static ArticleEntite toArticleEntity(ArticleValue pArticleValue, SousFamilleArticleEntity pSousFamilleEntite,
	    MetrageEntite pMetrageEntite, GrosseurEntite pGrosseurEntite, MatiereArticleEntite pMatiereEntite) {

	    ArticleEntite vArticleEntity = new ArticleEntite();

	    if (pArticleValue.getId() != null) {
	      vArticleEntity.setId(pArticleValue.getId());
	    }
	    /** The ref. */
	    vArticleEntity.setRef(pArticleValue.getRef());
	    /** Designation. */
	    vArticleEntity.setDesignation(pArticleValue.getDesignation());
	    /** prix unitaire */
	    vArticleEntity.setPu(pArticleValue.getPu());
	    /** pmp */
	    vArticleEntity.setPmp(pArticleValue.getPmp());
	    /** producteur. */
	    vArticleEntity.setProducteur(pArticleValue.getProducteur());
	    /** date introduction */
	    vArticleEntity.setDateIntroduction(pArticleValue.getDateIntroduction());
	    /** laize */
	    vArticleEntity.setLaize(pArticleValue.getLaize());
	    /** poids */
	    vArticleEntity.setPoids(pArticleValue.getPoids());
	    /** tare */
	    vArticleEntity.setTare(pArticleValue.getTare());
	    /** poids */
	    vArticleEntity.setPoidsBrut(pArticleValue.getPoidsBrut());
	    /** observations. */
	    vArticleEntity.setObservation(pArticleValue.getObservation());
	    /** emplacement. */
	    vArticleEntity.setEmplacement(pArticleValue.getEmplacement());
	    /** site */
	    vArticleEntity.setSiteEntite(pArticleValue.getSiteEntite());
	    /** Sous Famille */
	    vArticleEntity.setSousFamilleArtEntite(pSousFamilleEntite);
	    /** Site */
	    vArticleEntity.setSiteEntite(pArticleValue.getSiteEntite());
	    
	    
	    
	    vArticleEntity.setIdTaxe(pArticleValue.getIdTaxe());
	    
	    vArticleEntity.setTva(pArticleValue.getTva());
	    vArticleEntity.setPuTTC(pArticleValue.getPuTTC());
	    
	    vArticleEntity.setDateIntroduction(pArticleValue.getDateIntroduction());
	    
	    
	    
	    
	    
	    /** Grosseur */
	    if (pGrosseurEntite != null && pGrosseurEntite.getId() != null) {
	      vArticleEntity.setGrosseurEntite(pGrosseurEntite);
	    }
	    /** Metrage */
	    if (pMetrageEntite != null && pMetrageEntite.getId() != null) {
	      vArticleEntity.setMetrageEntite(pMetrageEntite);
	    }
	    /** Matiere */
	    if (pMatiereEntite != null && pMatiereEntite.getId() != null) {
	      vArticleEntity.setMatiereEntite(pMatiereEntite);
	    }
	    /** Unite */
	    vArticleEntity.setUniteEntite(pArticleValue.getUniteEntite());
	    /** Methode */
	    vArticleEntity.setMethodeGestion(pArticleValue.getMethodeGestion());
	    /** Emplacement */
	    vArticleEntity.setEmplacement(pArticleValue.getEmplacement());
	    
	    vArticleEntity.setUnite2Entite(pArticleValue.getUnite2Entite());
	    
	    vArticleEntity.setGrammage(pArticleValue.getGrammage());
	    vArticleEntity.setDimension(pArticleValue.getDimension());
	    
	    
	    
	    
	    vArticleEntity.setProduitId(pArticleValue.getProduitId());
	    vArticleEntity.setNbrCouleur(pArticleValue.getNbrCouleur());
	    vArticleEntity.setDimensionPapier(pArticleValue.getDimensionPapier());
	    vArticleEntity.setPiEntite(pArticleValue.getPiEntite());
	    vArticleEntity.setNbrPose(pArticleValue.getNbrPose());
	    vArticleEntity.setFichier(pArticleValue.getFichier());
	    vArticleEntity.setStockMin(pArticleValue.getStockMin());
	    
	    // Liste Document
	    if (pArticleValue.getDocumentEntites() != null) {

	      Set < DocumentArticleEntite > vListeDocuments = new HashSet < DocumentArticleEntite >();

	      for (DocumentArticleValue vDocumentValue : pArticleValue.getDocumentEntites()) {
	        DocumentArticleEntite vDe = toEntite(vDocumentValue);
	        vDe.setArticle(vArticleEntity);
	        vListeDocuments.add(vDe);
	      }

	      vArticleEntity.setDocumentEntites(vListeDocuments);

	    }
	    // Liste Seuils

	    if (pArticleValue.getSeuilEntities() != null) {

	      Set < SeuilArticleEntity > vListeSeuil = new HashSet < SeuilArticleEntity >();

	      for (SeuilArticleValue vSeuilValue : pArticleValue.getSeuilEntities()) {
	        SeuilArticleEntity vSe = toEntity(vSeuilValue);
	        vSe.setArticle(vArticleEntity);
	        vListeSeuil.add(vSe);
	      }

	      vArticleEntity.setSeuilEntites(vListeSeuil);

	    }

	    return vArticleEntity;
	  }

	  /**
	   * Converstion Article entity en value
	   **/
	  public static ArticleValue toArticleValue(ArticleEntite pArticleEntity) {

	    ArticleValue vArticleValue = new ArticleValue();

	    /** Id */
	    vArticleValue.setId(pArticleEntity.getId());

	    /** The ref. */
	    vArticleValue.setRef(pArticleEntity.getRef());
	    /** Designation. */
	    vArticleValue.setDesignation(pArticleEntity.getDesignation());
	    /** prix unitaire */
	    vArticleValue.setPu(pArticleEntity.getPu());
	    /** poids */
	    vArticleValue.setPoids(pArticleEntity.getPoids());
	    /** poids Brut */
	    vArticleValue.setPoidsBrut(pArticleEntity.getPoidsBrut());
	    /** tare */
	    vArticleValue.setTare(pArticleEntity.getTare());
	    /** pmp */
	    vArticleValue.setPmp(pArticleEntity.getPmp());
	    /** producteur. */
	    vArticleValue.setProducteur(pArticleEntity.getProducteur());
	    /** date introduction */
	    vArticleValue.setDateIntroduction(pArticleEntity.getDateIntroduction());
	    /** laize */
	    vArticleValue.setLaize(pArticleEntity.getLaize());
	    /** date introduction */
	    vArticleValue.setDateIntroduction(pArticleEntity.getDateIntroduction());
	    /** observations. */
	    vArticleValue.setObservation(pArticleEntity.getObservation());
	    /** emplacement. */
	    vArticleValue.setEmplacement(pArticleEntity.getEmplacement());
	    /** site */
	    vArticleValue.setSiteEntite(pArticleEntity.getSiteEntite());
	    
	    vArticleValue.setUnite2Entite(pArticleEntity.getUnite2Entite());
	    
	    
	    
	    vArticleValue.setTva(pArticleEntity.getTva());
	    vArticleValue.setPuTTC(pArticleEntity.getPuTTC());
	    vArticleValue.setIdTaxe(pArticleEntity.getIdTaxe());
	    
	    
	    
	    vArticleValue.setProduitId(pArticleEntity.getProduitId());
	    vArticleValue.setNbrCouleur(pArticleEntity.getNbrCouleur());
	    vArticleValue.setDimensionPapier(pArticleEntity.getDimensionPapier());
	    vArticleValue.setPiEntite(pArticleEntity.getPiEntite());
	    
	
	    
	    vArticleValue.setDateIntroduction(pArticleEntity.getDateIntroduction());
	    
	    
	    vArticleValue.setNbrPose(pArticleEntity.getNbrPose());
	    
	    vArticleValue.setFichier(pArticleEntity.getFichier());
	    /** Sous Famille */
	    if (pArticleEntity.getSousFamilleArtEntite() != null) {
	      vArticleValue.setSousFamilleArtEntite(pArticleEntity.getSousFamilleArtEntite().getId());
	      vArticleValue.setSousFamilleArtEntiteDesignation(pArticleEntity.getSousFamilleArtEntite().getDesignation());
	      vArticleValue.setFamilleArticleDesignation(pArticleEntity.getSousFamilleArtEntite().getFamilleArticle().getDesignation());
	    }
	    /** Site */
	    if (pArticleEntity.getSiteEntite() != null) {
	      vArticleValue.setSiteEntite(pArticleEntity.getSiteEntite());
	    }
	    /** Grosseur */
	    if (pArticleEntity.getGrosseurEntite() != null) {
	      vArticleValue.setGrosseurEntite(pArticleEntity.getGrosseurEntite().getId());
	    }
	    /** Metrage */
	    if (pArticleEntity.getMetrageEntite() != null) {
	      vArticleValue.setMetrageEntite(pArticleEntity.getMetrageEntite().getId());
	    }
	    /** Matière */
	    if (pArticleEntity.getMatiereEntite() != null) {
	      vArticleValue.setMatiereEntite(pArticleEntity.getMatiereEntite().getId());
	    }
	    /** Unite */
	    if (pArticleEntity.getUniteEntite() != null) {
	      vArticleValue.setUniteEntite(pArticleEntity.getUniteEntite());
	    }
	    /** Methode de Gestion */
	    if (pArticleEntity.getMethodeGestion() != null) {
	      vArticleValue.setMethodeGestion(pArticleEntity.getMethodeGestion());
	    }
	    /** Emplacement */
	    if (pArticleEntity.getEmplacement() != null) {
	      vArticleValue.setEmplacement(pArticleEntity.getEmplacement());
	    }
	    
	    
	    vArticleValue.setGrammage(pArticleEntity.getGrammage());
	    vArticleValue.setDimension(pArticleEntity.getDimension());
	    vArticleValue.setStockMin(pArticleEntity.getStockMin());
	    
	    // Liste Documents
	    if (pArticleEntity.getDocumentEntites() != null) {

	      Set < DocumentArticleValue > vListeDocuments = new HashSet < DocumentArticleValue >();

	      for (DocumentArticleEntite vDocumentEntity : pArticleEntity.getDocumentEntites()) {
	        DocumentArticleValue vDe = toValue(vDocumentEntity);

	        vListeDocuments.add(vDe);
	      }

	      vArticleValue.setDocumentEntites(vListeDocuments);

	    }
	    // Liste Seuils

	    if (pArticleEntity.getSeuilEntites() != null) {

	      Set < SeuilArticleValue > vListeSeuil = new HashSet < SeuilArticleValue >();

	      for (SeuilArticleEntity vSeuilEntity : pArticleEntity.getSeuilEntites()) {
	        SeuilArticleValue vSe = toValue(vSeuilEntity);

	        vListeSeuil.add(vSe);
	      }

	      vArticleValue.setSeuilEntities(vListeSeuil);

	    }

	    return vArticleValue;
	  }

	  /**
	   * Converstion entité à value du Document
	   * 
	   * @param
	   * @return
	   */
	  public static DocumentArticleEntite toEntite(DocumentArticleValue pDocumentValue) {
	    DocumentArticleEntite vDocumentEntite = new DocumentArticleEntite();
	    if (pDocumentValue.getId() != null) {
	      vDocumentEntite.setId(pDocumentValue.getId());
	    }
	    vDocumentEntite.setPath(pDocumentValue.getPath());
	    vDocumentEntite.setUidDocument(pDocumentValue.getUidDocument());
	    vDocumentEntite.setTypeDocumentEntite(pDocumentValue.getTypeDocumentEntite());

	    return vDocumentEntite;

	  }

	  /**
	   * Converstion value à entité du Document
	   * 
	   * @param
	   * @return
	   */
	  public static DocumentArticleValue toValue(DocumentArticleEntite pDocumentEntite) {
	    DocumentArticleValue vDocumentValue = new DocumentArticleValue();
	    if (pDocumentEntite.getId() != null) {
	      vDocumentValue.setId(pDocumentEntite.getId());
	    }
	    vDocumentValue.setPath(pDocumentEntite.getPath());
	    vDocumentValue.setUidDocument(pDocumentEntite.getUidDocument());
	    vDocumentValue.setTypeDocumentEntite(pDocumentEntite.getTypeDocumentEntite());

	    return vDocumentValue;

	  }
	  
	  
		/******************Option  Article Produit Entity To Value ****************************/
	  
	
		public static OptionArticleProduitValue toValue(OptionArticleProduitEntity pOptionArticleProduitEntity) {
			
			
			OptionArticleProduitValue optionArticleProduitValue = new OptionArticleProduitValue();
			optionArticleProduitValue.setId(pOptionArticleProduitEntity.getId());
			optionArticleProduitValue.setNom(pOptionArticleProduitEntity.getNom());
			optionArticleProduitValue.setOptionArticleId(pOptionArticleProduitEntity.getOptionArticleId());
			optionArticleProduitValue.setDesignation(pOptionArticleProduitEntity.getDesignation());
			
			optionArticleProduitValue.setFamilleOptionArticleDesignation(pOptionArticleProduitEntity.getFamilleOptionArticleDesignation());
			

			return optionArticleProduitValue;
		}

		// added by samer
	
		public static OptionArticleProduitEntity toEntity(OptionArticleProduitValue pOptionArticleProduitValue) {
			OptionArticleProduitEntity optionArticleProduitEntity = new OptionArticleProduitEntity();
			if (pOptionArticleProduitValue.getId() != null) {
				optionArticleProduitEntity.setId(pOptionArticleProduitValue.getId());
			}

			optionArticleProduitEntity.setNom(pOptionArticleProduitValue.getNom());
			optionArticleProduitEntity.setOptionArticleId(pOptionArticleProduitValue.getOptionArticleId());
			optionArticleProduitEntity.setDesignation(pOptionArticleProduitValue.getDesignation());
			
			optionArticleProduitEntity.setFamilleOptionArticleDesignation(pOptionArticleProduitValue.getFamilleOptionArticleDesignation());
			
			return optionArticleProduitEntity;
		}
		
		
		/******************OperationArticleProduit Entity To Value ****************************/
		  
		
		public static OperationArticleProduitValue toValue(OperationArticleProduitEntity pOptionArticleProduitEntity) {
			
			
			OperationArticleProduitValue optionArticleProduitValue = new OperationArticleProduitValue();
			
			optionArticleProduitValue.setId(pOptionArticleProduitEntity.getId());
			optionArticleProduitValue.setNom(pOptionArticleProduitEntity.getNom());
			optionArticleProduitValue.setOperationArticleId(pOptionArticleProduitEntity.getOperationArticleId());
			optionArticleProduitValue.setDesignation(pOptionArticleProduitEntity.getDesignation());
			
			
			optionArticleProduitValue.setCout(pOptionArticleProduitEntity.getCout());
			optionArticleProduitValue.setTemps(pOptionArticleProduitEntity.getTemps());
			optionArticleProduitValue.setOrdre(pOptionArticleProduitEntity.getOrdre());
			

			return optionArticleProduitValue;
		}

		// added by samer
	
		public static OperationArticleProduitEntity toEntity(OperationArticleProduitValue pOptionArticleProduitValue) {
			OperationArticleProduitEntity optionArticleProduitEntity = new OperationArticleProduitEntity();
			if (pOptionArticleProduitValue.getId() != null) {
				optionArticleProduitEntity.setId(pOptionArticleProduitValue.getId());
			}

			optionArticleProduitEntity.setNom(pOptionArticleProduitValue.getNom());
			optionArticleProduitEntity.setOperationArticleId(pOptionArticleProduitValue.getOperationArticleId());
			optionArticleProduitEntity.setDesignation(pOptionArticleProduitValue.getDesignation());
			
			
			optionArticleProduitEntity.setCout(pOptionArticleProduitValue.getCout());
			optionArticleProduitEntity.setTemps(pOptionArticleProduitValue.getTemps());
			optionArticleProduitEntity.setOrdre(pOptionArticleProduitValue.getOrdre());
			
			return optionArticleProduitEntity;
		}
		
	  
		/****************** Article Produit Entity To Value ****************************/
	  
		// added by zeineb g
		/** ArticleProduitValue to ArticleProduitValue **/
		public static ArticleProduitValue toValue(ArticleProduitEntity pArticleProduitEntity) {
			ArticleProduitValue articleProduitValue = new ArticleProduitValue();
			articleProduitValue.setId(pArticleProduitEntity.getId());
			articleProduitValue.setArticleId(pArticleProduitEntity.getArticleId());
			articleProduitValue.setReferenceArticle(pArticleProduitEntity.getReferenceArticle());
			articleProduitValue.setProduitSemiFini(pArticleProduitEntity.getProduitSemiFini());
			// documentProduitValue.setProduitId(pDocumentProduitEntity.getProduitId());

			articleProduitValue.setQte(pArticleProduitEntity.getQte());
			
			
			articleProduitValue.setImpressionProduitId(pArticleProduitEntity.getImpressionProduitId());
			
			articleProduitValue.setGrammage(pArticleProduitEntity.getGrammage());
						
			articleProduitValue.setDimension(pArticleProduitEntity.getDimension());
			
			articleProduitValue.setSousFamilleArticleId(pArticleProduitEntity.getSousFamilleArticleId());
			
			
			articleProduitValue.setInfoMatiere(pArticleProduitEntity.getInfoMatiere());
			
			
			// added by samer
			if (pArticleProduitEntity.getOptionArticleProduits() != null) {
				Set<OptionArticleProduitValue> vListeOptions = new HashSet<OptionArticleProduitValue>();
				for (OptionArticleProduitEntity vArticleEntity : pArticleProduitEntity.getOptionArticleProduits()) {
					OptionArticleProduitValue vDe = toValue(vArticleEntity);
					vListeOptions.add(vDe);
				}
				articleProduitValue.setOptionArticleProduits(vListeOptions);
			}
			
			// added by samer
			if (pArticleProduitEntity.getOperationArticleProduits() != null) {
				Set<OperationArticleProduitValue> vListeOperations = new HashSet<OperationArticleProduitValue>();
				for (OperationArticleProduitEntity vArticleEntity : pArticleProduitEntity.getOperationArticleProduits()) {
					OperationArticleProduitValue vDe = toValue(vArticleEntity);
					vListeOperations.add(vDe);
				}
				articleProduitValue.setOperationArticleProduits(vListeOperations);
			}


			

			return articleProduitValue;
		}

		// added by zeineb g
		/** ArticleProduitEntity to ArticleProduitEntite **/
		public static ArticleProduitEntity toEntity(ArticleProduitValue pArticleProduitValue) {
			ArticleProduitEntity articleProduitEntity = new ArticleProduitEntity();
			if (pArticleProduitValue.getId() != null) {
				articleProduitEntity.setId(pArticleProduitValue.getId());
			}
			articleProduitEntity.setArticleId(pArticleProduitValue.getArticleId());
			// documentProduitEntity.setProduitId(pDocumentProduitValue.getProduitId());
			articleProduitEntity.setQte(pArticleProduitValue.getQte());
			articleProduitEntity.setProduitSemiFini(pArticleProduitValue.getProduitSemiFini());
			articleProduitEntity.setReferenceArticle(pArticleProduitValue.getReferenceArticle());
			
			articleProduitEntity.setImpressionProduitId(pArticleProduitValue.getImpressionProduitId());
			
			articleProduitEntity.setGrammage(pArticleProduitValue.getGrammage());
			
			articleProduitEntity.setDimension(pArticleProduitValue.getDimension());
			
			
			articleProduitEntity.setSousFamilleArticleId(pArticleProduitValue.getSousFamilleArticleId());
			
			
			articleProduitEntity.setInfoMatiere(pArticleProduitValue.getInfoMatiere());
			
			if(pArticleProduitValue.getOptionArticleProduits() != null) {
				
				Set<OptionArticleProduitEntity> vListeOptionArticleProduit = new HashSet<OptionArticleProduitEntity>();
				
				for(OptionArticleProduitValue vOptionArticleProduit :pArticleProduitValue.getOptionArticleProduits() ) {
					
					OptionArticleProduitEntity   vOptionArticleProduitEntite = toEntity(vOptionArticleProduit);
					
					vOptionArticleProduitEntite.setArticleProduit(articleProduitEntity);
					
					vListeOptionArticleProduit.add(vOptionArticleProduitEntite);
				}
				
				
				articleProduitEntity.setOptionArticleProduits(vListeOptionArticleProduit);
				
			}
			
			
			
	         if(pArticleProduitValue.getOperationArticleProduits() != null) {
				
				Set<OperationArticleProduitEntity> vListeOperationArticleProduit = new HashSet<OperationArticleProduitEntity>();
				
				for(OperationArticleProduitValue vOptionArticleProduit :pArticleProduitValue.getOperationArticleProduits() ) {
					
					OperationArticleProduitEntity   vOptionArticleProduitEntite = toEntity(vOptionArticleProduit);
					
					vOptionArticleProduitEntite.setArticleProduit(articleProduitEntity);
					
					vListeOperationArticleProduit.add(vOptionArticleProduitEntite);
				}
				
				
				articleProduitEntity.setOperationArticleProduits(vListeOperationArticleProduit);
				
			}
			
			return articleProduitEntity;
		}

	/****************** Produit Entity To Value ****************************/
	public static ProduitValue toValue(ProduitEntity pProduitEntity) {
		ProduitValue produitValue = new ProduitValue();
		produitValue.setId(pProduitEntity.getId());
		produitValue.setDesignation(pProduitEntity.getDesignation());
		produitValue.setEtat(pProduitEntity.getEtat());
		produitValue.setDateIntroduction(pProduitEntity.getDateIntroduction());
		produitValue.setReference(pProduitEntity.getReference());
		produitValue.setSiteId(pProduitEntity.getSiteId());
		produitValue.setComposition(pProduitEntity.getComposition());
		produitValue.setPrixUnitaire(pProduitEntity.getPrixUnitaire());
		produitValue.setCodeCouleur(pProduitEntity.getCodeCouleur());
		produitValue.setReferenceInterne(pProduitEntity.getReferenceInterne());
		produitValue.setQuantite(pProduitEntity.getQuantite());
		produitValue.setPrixAchat(pProduitEntity.getPrixAchat());
	    produitValue.setIdTaxe(pProduitEntity.getIdTaxe());
	    produitValue.setUnite(pProduitEntity.getUnite());
		produitValue.setUniteSupplementaire(pProduitEntity.getUniteSupplementaire());
	    produitValue.setFond(pProduitEntity.getFond());
	    produitValue.setFondSupplementaire(pProduitEntity.getFondSupplementaire());
	    
	    produitValue.setTva(pProduitEntity.getTva());
	    produitValue.setTauxTVA(pProduitEntity.getTva());
	    produitValue.setSerialisable(pProduitEntity.isSerialisable());
	    
	    produitValue.setDescription(pProduitEntity.getDescription());
	    
	    produitValue.setPrixVenteTTC(pProduitEntity.getPrixVenteTTC());
	    
	    produitValue.setRetour(pProduitEntity.getRetour());
	    
	    produitValue.setBoutiqueId(pProduitEntity.getBoutiqueId());
	    
	    
	    produitValue.setCouleur(pProduitEntity.getCouleur());
	    produitValue.setNumero(pProduitEntity.getNumero());
	    produitValue.setReferenceFournisseur(pProduitEntity.getReferenceFournisseur());
	    produitValue.setPrixAchatTTC(pProduitEntity.getPrixAchatTTC());
	    produitValue.setStock(pProduitEntity.isStock());
	    
	    
	    
	    produitValue.setDimension(pProduitEntity.getDimension());
	    produitValue.setGrammage(pProduitEntity.getGrammage());
	    produitValue.setNature(pProduitEntity.getNature());
	    produitValue.setNbrPause(pProduitEntity.getNbrPause());
	    produitValue.setNumerotation(pProduitEntity.getNumerotation());
	    
	    produitValue.setCompteComptableId(pProduitEntity.getCompteComptableId());
	    
	    produitValue.setFodec(pProduitEntity.isFodec());
	    
	    
	    
	    
	    produitValue.setDevise(pProduitEntity.getDevise());
	    produitValue.setPantone(pProduitEntity.getPantone());
	    produitValue.setFormat(pProduitEntity.getFormat());
	    
	    
	    if (pProduitEntity.getSousFamille() != null) {
			produitValue.setSousFamilleId(pProduitEntity.getSousFamille()
					.getId());
			produitValue.setSousFamilleDesignation(pProduitEntity.getSousFamille()
					.getDesignation());
			
		}
		produitValue.setPartieIntersseId(pProduitEntity.getPartieIntersseId());
		/*** Liste Document produit */
		if (pProduitEntity.getDocumentProduits() != null) {
			Set<DocumentProduitValue> vListeDocuments = new HashSet<DocumentProduitValue>();
			for (DocumentProduitEntity vDocumentEntity : pProduitEntity
					.getDocumentProduits()) {
				DocumentProduitValue vDe = toValue(vDocumentEntity);
				vListeDocuments.add(vDe);
			}
			produitValue.setDocumentProduits(vListeDocuments);
		}
		
		
		/*** Article produit */
		// added by zeineb g
		if (pProduitEntity.getArticleProduits() != null) {
			Set<ArticleProduitValue> vListeArticles = new HashSet<ArticleProduitValue>();
			for (ArticleProduitEntity vArticleEntity : pProduitEntity.getArticleProduits()) {
				ArticleProduitValue vDe = toValue(vArticleEntity);
				vListeArticles.add(vDe);
			}
			produitValue.setArticleProduits(vListeArticles);
		}

	
		return produitValue;
	}

	/** produitArticleValue to produitEntite **/
	public static ProduitEntity toEntity(ProduitValue pProduitValue,
			SousFamilleProduitEntity pSousFamille) {
		ProduitEntity produiEntity = new ProduitEntity();
		if (pProduitValue.getId() != null) {
			produiEntity.setId(pProduitValue.getId());
		}
		produiEntity.setDesignation(pProduitValue.getDesignation());
		produiEntity.setEtat(pProduitValue.getEtat());
		produiEntity.setDateIntroduction(pProduitValue.getDateIntroduction());
		produiEntity.setReference(pProduitValue.getReference());
		produiEntity.setSiteId(pProduitValue.getSiteId());
		produiEntity.setPartieIntersseId(pProduitValue.getPartieIntersseId());
		produiEntity.setComposition(pProduitValue.getComposition());
		produiEntity.setSousFamille(pSousFamille);
		produiEntity.setPrixUnitaire(pProduitValue.getPrixUnitaire());
		produiEntity.setCodeCouleur(pProduitValue.getCodeCouleur());
		produiEntity.setReferenceInterne(pProduitValue.getReferenceInterne());
		produiEntity.setQuantite(pProduitValue.getQuantite());
		produiEntity.setPrixAchat(pProduitValue.getPrixAchat());
		produiEntity.setIdTaxe(pProduitValue.getIdTaxe());
		produiEntity.setUnite(pProduitValue.getUnite());
		produiEntity.setUniteSupplementaire(pProduitValue.getUniteSupplementaire());
		produiEntity.setFond(pProduitValue.getFond());
		produiEntity.setFondSupplementaire(pProduitValue.getFondSupplementaire());
		
		produiEntity.setTva(pProduitValue.getTva());
		produiEntity.setSerialisable(pProduitValue.isSerialisable());
		
		produiEntity.setDescription(pProduitValue.getDescription());
		
		produiEntity.setPrixVenteTTC(pProduitValue.getPrixVenteTTC());
		
		produiEntity.setRetour(pProduitValue.getRetour());
		
		produiEntity.setBoutiqueId(pProduitValue.getBoutiqueId());
		
		
	    
		produiEntity.setCouleur(pProduitValue.getCouleur());
		produiEntity.setNumero(pProduitValue.getNumero());
		produiEntity.setReferenceFournisseur(pProduitValue.getReferenceFournisseur());
		produiEntity.setPrixAchatTTC(pProduitValue.getPrixAchatTTC());
		produiEntity.setStock(pProduitValue.isStock());
		
		
		produiEntity.setDimension(pProduitValue.getDimension());
		produiEntity.setGrammage(pProduitValue.getGrammage());
		produiEntity.setNature(pProduitValue.getNature());
		produiEntity.setNbrPause(pProduitValue.getNbrPause());
		produiEntity.setNumerotation(pProduitValue.getNumerotation());
		
		produiEntity.setCompteComptableId(pProduitValue.getCompteComptableId());
		produiEntity.setFodec(pProduitValue.isFodec());
		
		
		produiEntity.setDevise(pProduitValue.getDevise());
		
		produiEntity.setPantone(pProduitValue.getPantone());
		produiEntity.setFormat(pProduitValue.getFormat());
		
		
		/*** Liste Document produit */
		if (pProduitValue.getDocumentProduits() != null) {
			Set<DocumentProduitEntity> vListeDocuments = new HashSet<DocumentProduitEntity>();
			for (DocumentProduitValue vDocumentValue : pProduitValue
					.getDocumentProduits()) {
				DocumentProduitEntity vDe = toEntity(vDocumentValue);
				vDe.setProuduit(produiEntity);
				vListeDocuments.add(vDe);
			}
			produiEntity.setDocumentProduits(vListeDocuments);
		}
		
		if (pProduitValue.getArticleProduits() != null) {
			Set<ArticleProduitEntity> vListeArticles = new HashSet<ArticleProduitEntity>();
			for (ArticleProduitValue vArticleValue : pProduitValue.getArticleProduits()) {
				ArticleProduitEntity vDe = toEntity(vArticleValue);
				vDe.setProduit(produiEntity);
				vListeArticles.add(vDe);
			}
			produiEntity.setArticleProduits(vListeArticles);		
		}

		//Added on 16/11/2016 , by Zeineb Medimagh
	
		return produiEntity;
	}

	/** DocumentProduitEntite to DocumentProduitValue **/
	public static DocumentProduitValue toValue(
			DocumentProduitEntity pDocumentProduitEntity) {
		DocumentProduitValue documentProduitValue = new DocumentProduitValue();
		documentProduitValue.setId(pDocumentProduitEntity.getId());
		documentProduitValue.setPath(pDocumentProduitEntity.getPath());
		// documentProduitValue.setProduitId(pDocumentProduitEntity.getProduitId());
		documentProduitValue.setUidDocument(pDocumentProduitEntity
				.getUidDocument());
		documentProduitValue.setTypeDocumentId(pDocumentProduitEntity
				.getTypeDocumentId());
		return documentProduitValue;
	}

	/** DocumentProduitValue to DocumentProduitEntite **/
	public static DocumentProduitEntity toEntity(
			DocumentProduitValue pDocumentProduitValue) {
		DocumentProduitEntity documentProduitEntity = new DocumentProduitEntity();
		if (pDocumentProduitValue.getId() != null) {
			documentProduitEntity.setId(pDocumentProduitValue.getId());
		}
		documentProduitEntity.setPath(pDocumentProduitValue.getPath());
		// documentProduitEntity.setProduitId(pDocumentProduitValue.getProduitId());
		documentProduitEntity.setUidDocument(pDocumentProduitValue
				.getUidDocument());
		documentProduitEntity.setTypeDocumentId(pDocumentProduitValue
				.getTypeDocumentId());
		return documentProduitEntity;
	}

	/** FamilleProduitEntite to familleProduitValue **/
	public static FamilleProduitValue toValue(
			FamilleProduitEntity entity) {
		FamilleProduitValue dto = new FamilleProduitValue();
		dto.setId(entity.getId());
		dto.setDesignation(entity.getDesignation());
		dto.setTva(entity.getTva());
		dto.setIdTaxe(entity.getIdTaxe());
		
		dto.setSerialisable(entity.isSerialisable());
		
		if(entity.getSuperfamilleProduit() != null) {
			
			dto.setSuperFamilleProduitId(entity.getSuperfamilleProduit().getId());
			dto.setSuperFamilleProduitDesignation(entity.getSuperfamilleProduit().getDesignation());
			
		}
		
		
		
		return dto;
	}

	/** FamilleProduitValue to familleProduitEntite **/
	public static FamilleProduitEntity toEntity(
			FamilleProduitValue pFamilleProduitValue) {
		FamilleProduitEntity familleProduitEntity = new FamilleProduitEntity();
		if (pFamilleProduitValue.getId() != null) {
			familleProduitEntity.setId(pFamilleProduitValue.getId());
		}
		familleProduitEntity.setDesignation(pFamilleProduitValue
				.getDesignation());
		familleProduitEntity.setTva(pFamilleProduitValue.getTva());
		
		familleProduitEntity.setIdTaxe(pFamilleProduitValue.getIdTaxe());
		
		familleProduitEntity.setSerialisable(pFamilleProduitValue.isSerialisable());
		
		
		if(pFamilleProduitValue.getSuperFamilleProduitId() != null) {
			
			SuperFamilleProduitEntity superFamilleProduitEntity = new SuperFamilleProduitEntity();
			superFamilleProduitEntity.setId(pFamilleProduitValue.getSuperFamilleProduitId());
			
			
			familleProduitEntity.setSuperfamilleProduit(superFamilleProduitEntity);
		}
		
		
		
		return familleProduitEntity;
	}

	/** FamilleEntite to familleValue **/
	public static FamilleValue toValue(FamilleEntite pFamilleEntity) {
		FamilleValue familleValue = new FamilleValue();
		familleValue.setId(pFamilleEntity.getId());
		familleValue.setDesignation(pFamilleEntity.getDesignation());
		familleValue.setAchat(pFamilleEntity.isAchat());
		familleValue.setVente(pFamilleEntity.isVente());
		return familleValue;
	}

	/** FamilleValue to familleEntite **/
	public static FamilleEntite toEntity(FamilleValue pFamilleValue) {
		FamilleEntite familleEntity = new FamilleEntite();
		if (pFamilleValue.getId() != null) {
			familleEntity.setId(pFamilleValue.getId());
		}
		familleEntity.setDesignation(pFamilleValue.getDesignation());
		familleEntity.setAchat(pFamilleValue.isAchat());
		familleEntity.setVente(pFamilleValue.isVente());
		return familleEntity;
	}

	/**** listValue produit to liste entity ******/
	public static List<ProduitValue> tolistProduitsValues(
			List<ProduitEntity> pProduitEntitys) {
		List<ProduitValue> vProduits = new ArrayList<ProduitValue>();
		for (ProduitEntity vProduitEntite : pProduitEntitys) {
			vProduits.add(PersistanceUtilities.toValue(vProduitEntite));
		}
		return vProduits;
	}

	/**** listValue sous famille produit to liste entity ******/
	public static List<SousFamilleProduitValue> tolistSousFamilleProduitValues(
			List<SousFamilleProduitEntity> pSousFamilleEntites) {
		List<SousFamilleProduitValue> vSousFamilleProduitValues = new ArrayList<SousFamilleProduitValue>();
		for (SousFamilleProduitEntity sousFamilleEntite : pSousFamilleEntites) {
			vSousFamilleProduitValues.add(PersistanceUtilities
					.toValue(sousFamilleEntite));
		}
		return vSousFamilleProduitValues;
	}

	/**** listValue famille produit to liste entity ******/
	public static List<FamilleProduitValue> tolistFamilleProduitValues(
			List<FamilleProduitEntity> pFamilleEntites) {
		List<FamilleProduitValue> vFamilleProduitValues = new ArrayList<FamilleProduitValue>();
		for (FamilleProduitEntity familleEntite : pFamilleEntites) {
			vFamilleProduitValues.add(PersistanceUtilities
					.toValue(familleEntite));
		}
		return vFamilleProduitValues;
	}

	/**** listValue document produit to liste entity ******/
	public static List<DocumentProduitValue> tolistDocumentProduitValues(
			List<DocumentProduitEntity> pDocumentEntites) {
		List<DocumentProduitValue> vDocumentProduitValues = new ArrayList<DocumentProduitValue>();
		for (DocumentProduitEntity documentEntite : pDocumentEntites) {
			vDocumentProduitValues.add(PersistanceUtilities
					.toValue(documentEntite));
		}
		return vDocumentProduitValues;
	}

	
	/**** Region ****/
	/** Converstion RegionValue en RegionEntite **/
	public static RegionEntite toEntity(RegionValue value) {
		RegionEntite entity = new RegionEntite();
		if (value.getId() != null) {
			entity.setId(value.getId());
		}
		entity.setDesignation(value.getDesignation());
		return entity;
	}

	/** Converstion RegionEntite entite en RegionValue **/
	public static RegionValue toValue(RegionEntite entite) {
		RegionValue value = new RegionValue();
		value.setId(entite.getId());
		value.setDesignation(entite.getDesignation());
		return value;
	}
	
	/**
	 * Accesseur en lecture de l'attribut <code>vGestionnaireDocument</code>.
	 * 
	 * @return IGestionnaireDocument L'attribut vGestionnaireDocument à lire.
	 */
	public IGestionnaireDocument getvGestionnaireDocument() {
		return vGestionnaireDocument;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>vGestionnaireDocument</code>.
	 *
	 * @param vGestionnaireDocument
	 *            L'attribut vGestionnaireDocument à modifier.
	 */
	public void setvGestionnaireDocument(
			IGestionnaireDocument vGestionnaireDocument) {
		this.vGestionnaireDocument = vGestionnaireDocument;
	}
	
	public static UserValue toUserValue(UserEntity entity) {
		
		UserValue dto = new UserValue();
		
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setEmail(entity.getEmail());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setUserName(entity.getUserName());
		dto.setPassword(entity.getPassword());
		dto.setEnabled(entity.getEnabled());
		
		dto.setRoleNames(entity.getRoleNames());
		
		dto.setBoutiqueId(entity.getBoutiqueId());
		dto.setJob(entity.getJob());
		 if(dto.getRoles() !=null)
		 {
				Collection<RoleValue> list = new ArrayList<RoleValue>();

		 
			 for (RoleEntity v : entity.getRoles()) {
					list.add(toRoleValue(v));
					dto.setRoles(list);

				}
		 }
		
		return dto;
	}

	public static UserEntity toUserEntity(UserValue dto) {
		
		UserEntity entity = new UserEntity();
		
		entity.setId(dto.getId());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setUserName(dto.getUserName());
		entity.setPassword(dto.getPassword());
		entity.setEnabled(dto.getEnabled());
		
		entity.setRoleNames(dto.getRoleNames());
		
		entity.setBoutiqueId(dto.getBoutiqueId());
		
		entity.setJob(dto.getJob());

 if(dto.getRoles() !=null)
 {
		Collection<RoleEntity> list = new ArrayList<RoleEntity>();

 
	 for (RoleValue v : dto.getRoles()) {
			list.add(toRoleEntity(v));
			 entity.setRoles(list);

		}
 }
		
		return entity;
	}

	public static List<UserValue> listUserToValue(List<UserEntity> listEntity) {
		
		List<UserValue> list = new ArrayList<UserValue>();
		
		for(UserEntity entity : listEntity){
			list.add(toUserValue(entity));
		}
		
		return list;
	}
	
	public static RoleValue toRoleValue(RoleEntity entity) {
		 RoleValue dto = new  RoleValue();
		 
		 dto.setId(entity.getId());
		 dto.setCode(entity.getCode());
		 dto.setModule(entity.getModule());
		//dto.setBlSuppression(entity.getBlSuppression());
		// dto.setDateCreation(entity.getDateCreation());
		// dto.setDateModification(entity.getDateModification());
		// dto.setDateSuppression(entity.getDateSuppression());
		 dto.setDesignation(entity.getDesignation());
	//	 dto.setVersion(entity.getVersion());
		// dto.setDescription(entity.getDescription());
		 return dto ;
	}
	public static RoleEntity toRoleEntity(RoleValue dto){
		RoleEntity entity = new RoleEntity ();
		
		entity.setId(dto.getId());
		entity.setCode(dto.getCode());
		entity.setModule(dto.getModule());
		//entity.setBlSuppression(dto.getBlSuppression());
	//	entity.setDateCreation(dto.getDateCreation());
	//	entity.setDateModification(dto.getDateModification());
	//	entity.setDateSuppression(dto.getDateSuppression());
		entity.setDesignation(dto.getDesignation());
	//	entity.setVersion(dto.getVersion());
	//	entity.setDescription(dto.getDescription());
		return entity;
	}
	
	public static List<RoleValue> listRoleToValue(List<RoleEntity> listEntity) {
		
		List<RoleValue> list = new ArrayList<RoleValue>();
		
		for(RoleEntity entity : listEntity){
			list.add(toRoleValue(entity));
		}
		
		return list;
	}
	
	//added on 25 03 2018
	
	/*********************/ 
	/**
	 * Converstion value à entity du PrixClient/*
	 * 
	 * @param
	 * @return
	 */
	public static PrixClientEntity toEntity(
			PrixClientValue pPrixClientValue) {
		PrixClientEntity vPrixClientEntity = new PrixClientEntity();

		/** The id. */
		if (pPrixClientValue.getId() != null) {
			vPrixClientEntity.setId(pPrixClientValue.getId());
		}
		/** The blsuppression. */
		
		/** The prixvente . */
		vPrixClientEntity.setPrixvente(pPrixClientValue.getPrixvente());
		/** The ebproduitid . */
		vPrixClientEntity.setEbproduitid(pPrixClientValue.getEbproduit());
		/** The idpi . */
		vPrixClientEntity.setPartieinteresse(pPrixClientValue.getIdpi());

		vPrixClientEntity.setFamillePartieInteressee(pPrixClientValue.getFamillePartieInteressee());
		
		vPrixClientEntity.setRemise(pPrixClientValue.getRemise());

		return vPrixClientEntity;
		
		
	}

	/**
	 * Converstion entité à value du PrixClient/*
	 * 
	 * @param
	 * @return
	 */
	public static PrixClientValue toValue(
			PrixClientEntity pPrixClientEntity) {
		PrixClientValue vPrixClientValue = new PrixClientValue();

		/** The id. */

		vPrixClientValue.setId(pPrixClientEntity.getId());

		/** The blsuppression. */
		vPrixClientValue.setBlsuppression(pPrixClientEntity.equals(pPrixClientEntity));
		/** The datedeb. */
		vPrixClientValue.setDatedeb(pPrixClientEntity.getDatedeb());
		/** The datecreation. */
		vPrixClientValue.setDatecreation(pPrixClientEntity.getDatecreation());
		/** The datesuppression. */
		vPrixClientValue.setDatesuppresion(pPrixClientEntity.getDatesuppresion());
		/** The datemodification. */
		vPrixClientValue.setDatemodification(pPrixClientEntity.getDatemodification());
		
		/** The prixvente . */
		vPrixClientValue.setPrixvente(pPrixClientEntity.getPrixvente());
		
	
			vPrixClientValue.setEbproduit(pPrixClientEntity.getEbproduitid());
		
		
		/** The idpi . */
	
			vPrixClientValue.setIdpi(pPrixClientEntity.getPartieinteresse());
	
		
			vPrixClientValue.setFamillePartieInteressee(pPrixClientEntity.getFamillePartieInteressee());
			
			vPrixClientValue.setRemise(pPrixClientEntity.getRemise());
		
		
		return vPrixClientValue;
		
	}
	
	/**** listValue PrixClient  to liste entity ******/
	public static List<PrixClientValue> tolistPrixClientValues(
			List<PrixClientEntity> pPrixClientEntites) {
		List<PrixClientValue> vPrixClientValues = new ArrayList<PrixClientValue>();
		for (PrixClientEntity PrixClientEntite : pPrixClientEntites) {
			vPrixClientValues.add(PersistanceUtilities
					.toValue(PrixClientEntite));
		}
		return vPrixClientValues;
	}
	

	/**** listEntity PrixClient  to liste Value ******/
	public static List<PrixClientEntity> tolistPrixClientEntity(
			List<PrixClientValue> pPrixClientValue) {
		List<PrixClientEntity> vPrixClientEntity = new ArrayList<PrixClientEntity>();
		for (PrixClientValue PrixClientValue : pPrixClientValue) {
			vPrixClientEntity.add(PersistanceUtilities
					.toEntity(PrixClientValue));
		}
		return vPrixClientEntity;
	}
	
	
	/**
	 * Converstion value à entity du ProduitSerialisableValue/*
	 * 
	 * @param
	 * @return
	 */
	public static ProduitSerialisableEntity toEntity(
			ProduitSerialisableValue pProduitSerialisableValue) {
		ProduitSerialisableEntity vProduitSerialisableEntity = new ProduitSerialisableEntity();

		/** The id. */
		if (pProduitSerialisableValue.getId() != null) {
			vProduitSerialisableEntity.setId(pProduitSerialisableValue.getId());
		}
	
		vProduitSerialisableEntity.setProduitId(pProduitSerialisableValue.getProduitId());
		vProduitSerialisableEntity.setClientId(pProduitSerialisableValue.getClientId());
		vProduitSerialisableEntity.setFournisseurId(pProduitSerialisableValue.getFournisseurId());
		vProduitSerialisableEntity.setDateAchat(pProduitSerialisableValue.getDateAchat());
		vProduitSerialisableEntity.setDateVente(pProduitSerialisableValue.getDateVente());
		vProduitSerialisableEntity.setNumBonReception(pProduitSerialisableValue.getNumBonReception());
		vProduitSerialisableEntity.setNumBonLivraison(pProduitSerialisableValue.getNumBonLivraison());
		vProduitSerialisableEntity.setPrixAchat(pProduitSerialisableValue.getPrixAchat());
		vProduitSerialisableEntity.setPrixVente(pProduitSerialisableValue.getPrixVente());
		vProduitSerialisableEntity.setDateFinGarantie(pProduitSerialisableValue.getDateFinGarantie());
		vProduitSerialisableEntity.setNumSerie(pProduitSerialisableValue.getNumSerie());
		
		vProduitSerialisableEntity.setNumFacture(pProduitSerialisableValue.getNumFacture());
		vProduitSerialisableEntity.setDateFacture(pProduitSerialisableValue.getDateFacture());
		
		vProduitSerialisableEntity.setBlAncien(pProduitSerialisableValue.getBlAncien());
		vProduitSerialisableEntity.setBrRetour(pProduitSerialisableValue.getBrRetour());
		
		vProduitSerialisableEntity.setNumFactureAvoir(pProduitSerialisableValue.getNumFactureAvoir());
		vProduitSerialisableEntity.setFactureAncien(pProduitSerialisableValue.getFactureAncien());
		vProduitSerialisableEntity.setFactureAvoirAncien(pProduitSerialisableValue.getFactureAvoirAncien());
		
		
		vProduitSerialisableEntity.setBoutiqueId(pProduitSerialisableValue.getBoutiqueId());
		vProduitSerialisableEntity.setMagasinId(pProduitSerialisableValue.getMagasinId());
		vProduitSerialisableEntity.setHistoriqueBTreception(pProduitSerialisableValue.getHistoriqueBTreception());
		vProduitSerialisableEntity.setHistoriqueBTsortie(pProduitSerialisableValue.getHistoriqueBTsortie());
		
		
		vProduitSerialisableEntity.setMagasinDesignation(pProduitSerialisableValue.getMagasinDesignation());
		vProduitSerialisableEntity.setHistoriqueBSsortie(pProduitSerialisableValue.getHistoriqueBSsortie());
		
		return vProduitSerialisableEntity;
		
		
	}

	/**
	 * Converstion  entity a value  ProduitSerialisable/*
	 * 
	 * @param
	 * @return
	 */
	public static ProduitSerialisableValue  toValue(
			ProduitSerialisableEntity pProduitSerialisableEntity) {
		ProduitSerialisableValue vProduitSerialisableValue = new ProduitSerialisableValue();

		/** The id. */
		vProduitSerialisableValue.setId(pProduitSerialisableEntity.getId());
	
	
		vProduitSerialisableValue.setProduitId(pProduitSerialisableEntity.getProduitId());
		vProduitSerialisableValue.setClientId(pProduitSerialisableEntity.getClientId());
		vProduitSerialisableValue.setFournisseurId(pProduitSerialisableEntity.getFournisseurId());
		vProduitSerialisableValue.setDateAchat(pProduitSerialisableEntity.getDateAchat());
		vProduitSerialisableValue.setDateVente(pProduitSerialisableEntity.getDateVente());
		vProduitSerialisableValue.setNumBonReception(pProduitSerialisableEntity.getNumBonReception());
		vProduitSerialisableValue.setNumBonLivraison(pProduitSerialisableEntity.getNumBonLivraison());
		vProduitSerialisableValue.setPrixAchat(pProduitSerialisableEntity.getPrixAchat());
		vProduitSerialisableValue.setPrixVente(pProduitSerialisableEntity.getPrixVente());
		vProduitSerialisableValue.setDateFinGarantie(pProduitSerialisableEntity.getDateFinGarantie());
		vProduitSerialisableValue.setNumSerie(pProduitSerialisableEntity.getNumSerie());
		
		vProduitSerialisableValue.setNumFacture(pProduitSerialisableEntity.getNumFacture());
		vProduitSerialisableValue.setDateFacture(pProduitSerialisableEntity.getDateFacture());
		
		
		vProduitSerialisableValue.setBlAncien(pProduitSerialisableEntity.getBlAncien());
		vProduitSerialisableValue.setBrRetour(pProduitSerialisableEntity.getBrRetour());
		
		vProduitSerialisableValue.setNumFactureAvoir(pProduitSerialisableEntity.getNumFactureAvoir());
		vProduitSerialisableValue.setFactureAncien(pProduitSerialisableEntity.getFactureAncien());
		vProduitSerialisableValue.setFactureAvoirAncien(pProduitSerialisableEntity.getFactureAvoirAncien());
		
		
		
		vProduitSerialisableValue.setBoutiqueId(pProduitSerialisableEntity.getBoutiqueId());
		vProduitSerialisableValue.setMagasinId(pProduitSerialisableEntity.getMagasinId());
		vProduitSerialisableValue.setHistoriqueBTreception(pProduitSerialisableEntity.getHistoriqueBTreception());
		vProduitSerialisableValue.setHistoriqueBTsortie(pProduitSerialisableEntity.getHistoriqueBTsortie());
		
		vProduitSerialisableValue.setMagasinDesignation(pProduitSerialisableEntity.getMagasinDesignation());
		
		vProduitSerialisableValue.setHistoriqueBSsortie(pProduitSerialisableEntity.getHistoriqueBSsortie());
		
		
		return vProduitSerialisableValue;
		
		
	}
	
	
	/**
	 * Méthode permettant de convertir un objet valeur en entité.
	 */
	public static GroupeClientEntite toEntity(GroupeClientValue categorieValue) {
		GroupeClientEntite categorieEntite = new GroupeClientEntite();
		if (categorieValue.getId() != null) {
			categorieEntite.setId(categorieValue.getId());
		}
		categorieEntite.setDesignation(categorieValue.getDesignation());
		
		categorieEntite.setFamillePartieInteressee(categorieValue.getFamillePartieInteressee());
		
		categorieEntite.setCodeEntreprise(categorieValue.getCodeEntreprise());

		return categorieEntite;
	}

	/** ConverstionGroupeClientINT entite en GroupeClientINT value **/
	public static GroupeClientValue toValue(GroupeClientEntite categorieEntite) {
		
		if(categorieEntite != null) {
			GroupeClientValue categorieValue = new GroupeClientValue();

			categorieValue.setId(categorieEntite.getId());
			categorieValue.setDesignation(categorieEntite.getDesignation());
			
			categorieValue.setFamillePartieInteressee(categorieEntite.getFamillePartieInteressee());
			
			categorieValue.setCodeEntreprise(categorieEntite.getCodeEntreprise());
			
			return categorieValue;

		}
	
		return null;
	}
	
	
	/******************** package  ****************/


	public static PackageEntite toPackageEntity(
			PackageValue packageValue) {

		PackageEntite vPackageEntite = new PackageEntite();

		if (packageValue.getId() != null) {
			vPackageEntite.setId(packageValue.getId());
		}
		
		vPackageEntite.setReference(packageValue.getReference());
		vPackageEntite.setNom(packageValue.getNom());
		vPackageEntite.setDateDebut(packageValue.getDateDebut());
		vPackageEntite.setDateFin(packageValue.getDateFin());
		
		vPackageEntite.setClientId(packageValue.getClientId());
		vPackageEntite.setGroupeId(packageValue.getGroupeId());

		vPackageEntite.setBoutiqueId(packageValue.getBoutiqueId());
		
		
		if(packageValue.getListDetPackage() != null) {
			Set<DetailsPackageEntite> listDetPackage = new HashSet<>();
			
			for(DetailsPackageValue detailValue : packageValue.getListDetPackage()) {
				
				DetailsPackageEntite detailEntite = toDetailsPackageEntity(detailValue);
				
				detailEntite.setPack(vPackageEntite);
				
				listDetPackage.add(detailEntite);
			}
			
			
			vPackageEntite.setListDetPackage(listDetPackage);
			
		}




		return vPackageEntite;
	}

	/**
	 * Converstion PackageValue entity en value
	 **/
	public static PackageValue toPackageValue(
			PackageEntite vPackageEntite) {

		PackageValue packageValue = new PackageValue();

		packageValue.setId(vPackageEntite.getId());
		packageValue.setReference(vPackageEntite.getReference());
		packageValue.setNom(vPackageEntite.getNom());
		packageValue.setDateDebut(vPackageEntite.getDateDebut());
		packageValue.setDateFin(vPackageEntite.getDateFin());
		
		packageValue.setClientId(vPackageEntite.getClientId());
		packageValue.setGroupeId(vPackageEntite.getGroupeId());

		packageValue.setBoutiqueId(vPackageEntite.getBoutiqueId());
		
		
		if(vPackageEntite.getListDetPackage() != null) {
			List<DetailsPackageValue> listDetPackage = new ArrayList<>();
			
			for(DetailsPackageEntite detailEntite : vPackageEntite.getListDetPackage()) {
				
				DetailsPackageValue detailValue = toDetailsPackageValue(detailEntite);
				
				listDetPackage.add(detailValue);
			}
			
			
			packageValue.setListDetPackage(listDetPackage);
			
		}
	

		return packageValue;
	}

	
	
    /****************  detail package    ****************/
	

	public static DetailsPackageEntite toDetailsPackageEntity(
			DetailsPackageValue detailPackageValue) {

		DetailsPackageEntite vDetailPackageEntite = new DetailsPackageEntite();

		if (detailPackageValue.getId() != null) {
			vDetailPackageEntite.setId(detailPackageValue.getId());
		}
		
		vDetailPackageEntite.setProduitId(detailPackageValue.getProduitId());
		vDetailPackageEntite.setRemise(detailPackageValue.getRemise());
		vDetailPackageEntite.setObservations(detailPackageValue.getObservations());
		
		if(detailPackageValue.getPackageId() != null) {
			
			PackageEntite pack = new PackageEntite();
			
			pack.setId(detailPackageValue.getPackageId());
			
			vDetailPackageEntite.setPack(pack);
			
		}




		return vDetailPackageEntite;
	}

	/**
	 * Converstion detail PackageValue entity en value
	 **/
	public static DetailsPackageValue toDetailsPackageValue(
			DetailsPackageEntite vDetPackageEntite) {

		DetailsPackageValue detPackageValue = new DetailsPackageValue();

		detPackageValue.setId(vDetPackageEntite.getId());
		detPackageValue.setProduitId(vDetPackageEntite.getProduitId());
		detPackageValue.setRemise(vDetPackageEntite.getRemise());
		detPackageValue.setObservations(vDetPackageEntite.getObservations());
		
		if(vDetPackageEntite.getPack() != null) {
			detPackageValue.setPackageId(vDetPackageEntite.getPack().getId());
		}
	

		return detPackageValue;
	}
	
	
	
	                             /***********************  Societe   **********************/
	
	

	public static SocieteEntite toSocieteEntity(
			SocieteValue pSocieteValue) {

		SocieteEntite vSocieteEntite = new SocieteEntite();

		if (pSocieteValue.getId() != null) {
			vSocieteEntite.setId(pSocieteValue.getId());
		}
		/** The ref. */
		vSocieteEntite.setReference(pSocieteValue
				.getReference());
		/** raison sociale. */
		vSocieteEntite.setRaisonSociale(pSocieteValue
				.getRaisonSociale());
		/** Abréviation. */
		vSocieteEntite.setAbreviation(pSocieteValue
				.getAbreviation());
		/** devise. */
		vSocieteEntite.setDevise(pSocieteValue.getDevise());
		/** Activité */
		vSocieteEntite.setActivite(pSocieteValue.getActivite());
		/** Observation. */
		vSocieteEntite.setObservation(pSocieteValue
				.getObservation());
		/** date introduction */
		vSocieteEntite.setDateIntroduction(pSocieteValue
				.getDateIntroduction());
		/** Matricule fiscale. */
		vSocieteEntite.setMatrFiscal(pSocieteValue
				.getMatriculeFiscal());
		/** regime commercial. */
		vSocieteEntite.setRegimeCommercial(pSocieteValue
				.getRegimeCommercial());
		/** code douane. */
		vSocieteEntite.setCodeDouane(pSocieteValue
				.getCodeDouane());
		/** adresse. */
		vSocieteEntite.setAdresse(pSocieteValue.getAdresse());
		/** email */
		vSocieteEntite.setEmail(pSocieteValue.getEmail());
		/** telephone */
		vSocieteEntite.setTelephone(pSocieteValue
				.getTelephone());
		/** fax */
		vSocieteEntite.setFax(pSocieteValue.getFax());
		/** actif */
		vSocieteEntite.setActif(pSocieteValue.getActif());

		
		/** region */
		vSocieteEntite.setRegionId(pSocieteValue
				.getRegionId());
		

		vSocieteEntite.setObjectif(pSocieteValue.getObjectif());
		
		
		
		
		if(pSocieteValue.getBoutiques() != null) {
			Set<BoutiqueEntite> boutiques = new HashSet<>();
			
			for(BoutiqueValue detailValue : pSocieteValue.getBoutiques()) {
				
				BoutiqueEntite detailEntite = toBoutiqueEntity(detailValue);
				
				detailEntite.setSociete(vSocieteEntite);
				
				boutiques.add(detailEntite);
			}
			
			
			vSocieteEntite.setBoutiques(boutiques);
			
		}
		


		return vSocieteEntite;
	}

	
	public static SocieteValue toSocieteValue(
			SocieteEntite pSocieteEntity) {

		SocieteValue vSocieteValue = new SocieteValue();

		vSocieteValue.setId(pSocieteEntity.getId());

		/** The ref. */
		vSocieteValue.setReference(pSocieteEntity
				.getReference());
		/** raison sociale. */
		vSocieteValue.setRaisonSociale(pSocieteEntity
				.getRaisonSociale());
		/** Abréviation. */
		vSocieteValue.setAbreviation(pSocieteEntity
				.getAbreviation());
		/** devise. */
		vSocieteValue.setDevise(pSocieteEntity.getDevise());
		/** Activité */
		vSocieteValue.setActivite(pSocieteEntity.getActivite());
		/** Observation. */
		vSocieteValue.setObservation(pSocieteEntity
				.getObservation());
		/** date introduction */
		vSocieteValue.setDateIntroduction(pSocieteEntity
				.getDateIntroduction());
		/** Matricule fiscale. */
		vSocieteValue.setMatriculeFiscal(pSocieteEntity
				.getMatrFiscal());
		/** regime commercial. */
		vSocieteValue.setRegimeCommercial(pSocieteEntity
				.getRegimeCommercial());
		/** code douane. */
		vSocieteValue.setCodeDouane(pSocieteEntity
				.getCodeDouane());
		/** adresse. */
		vSocieteValue.setAdresse(pSocieteEntity.getAdresse());
		/** email */
		vSocieteValue.setEmail(pSocieteEntity.getEmail());
		/** telephone */
		vSocieteValue.setTelephone(pSocieteEntity
				.getTelephone());
		/** fax */
		vSocieteValue.setFax(pSocieteEntity.getFax());
		/** actif */
		vSocieteValue.setActif(pSocieteEntity.getActif());
	

		/** region */
		vSocieteValue
				.setRegionId(pSocieteEntity
						.getRegionId());
		
		
		vSocieteValue.setObjectif(pSocieteEntity.getObjectif());
		
		
		
		if(pSocieteEntity.getBoutiques() != null) {
			List<BoutiqueValue> boutiques = new ArrayList<>();
			
			for(BoutiqueEntite detailEntite : pSocieteEntity.getBoutiques()) {
				
				BoutiqueValue detailValue = toBoutiqueValue(detailEntite);
				
				boutiques.add(detailValue);
			}
			
			
			vSocieteValue.setBoutiques(boutiques);
			
		}
		
		
		

		return vSocieteValue;
	}

	
	
	
	//Super fammille
	
	
	/** SuperFamilleProduitEntite to SuperfamilleProduitValue **/
	public static SuperFamilleProduitValue toValue(
			SuperFamilleProduitEntity entity) {
		SuperFamilleProduitValue dto = new SuperFamilleProduitValue();
		dto.setId(entity.getId());
		dto.setDesignation(entity.getDesignation());
		dto.setTva(entity.getTva());
		dto.setIdTaxe(entity.getIdTaxe());
		
		
		
		
		return dto;
	}

	/** FamilleProduitValue to familleProduitEntite **/
	public static SuperFamilleProduitEntity toEntity(
			SuperFamilleProduitValue pFamilleProduitValue) {
		SuperFamilleProduitEntity familleProduitEntity = new SuperFamilleProduitEntity();
		if (pFamilleProduitValue.getId() != null) {
			familleProduitEntity.setId(pFamilleProduitValue.getId());
		}
		familleProduitEntity.setDesignation(pFamilleProduitValue
				.getDesignation());
		familleProduitEntity.setTva(pFamilleProduitValue.getTva());
		
		familleProduitEntity.setIdTaxe(pFamilleProduitValue.getIdTaxe());
		
		return familleProduitEntity;
	}
	
	
	
	/**** listValue super famille produit to liste entity ******/
	public static List<SuperFamilleProduitValue> tolistSuperFamilleProduitValues(
			List<SuperFamilleProduitEntity> pFamilleEntites) {
		List<SuperFamilleProduitValue> vFamilleProduitValues = new ArrayList<SuperFamilleProduitValue>();
		for (SuperFamilleProduitEntity familleEntite : pFamilleEntites) {
			vFamilleProduitValues.add(PersistanceUtilities
					.toValue(familleEntite));
		}
		return vFamilleProduitValues;
	}
	
	
	
	/*** Impression Produit  **/

	  /** unitelArticleEntite to uniteArticleValue **/
	  public static ImpressionProduitValue toValue(ImpressionProduitEntity pUniteArticleEntity) {
	    ImpressionProduitValue uniteArticleValue = new ImpressionProduitValue();
	    uniteArticleValue.setId(pUniteArticleEntity.getId());
	    uniteArticleValue.setDesignation(pUniteArticleEntity.getDesignation());
	    uniteArticleValue.setDescription(pUniteArticleEntity.getDescription());
	    return uniteArticleValue;
	  }

	  /** uniteArticleValue to uniteArticleEntite **/
	  public static ImpressionProduitEntity toEntity(ImpressionProduitValue pUniteArticleValue) {
	    ImpressionProduitEntity uniteArticleEntity = new ImpressionProduitEntity();
	    if (pUniteArticleValue.getId() != null) {
	      uniteArticleEntity.setId(pUniteArticleValue.getId());
	    }
	    uniteArticleEntity.setDesignation(pUniteArticleValue.getDesignation());
	    uniteArticleEntity.setDescription(pUniteArticleValue.getDescription());
	    return uniteArticleEntity;
	  }
	  
	  
		/*** Compte Comptable  **/

	  /** unitelArticleEntite to uniteArticleValue **/
	  public static CompteComptableValue toValue(CompteComptableEntity pCompteComptableEntity) {
		  CompteComptableValue compteComptableValue = new CompteComptableValue();
		  compteComptableValue.setId(pCompteComptableEntity.getId());
		  compteComptableValue.setDesignation(pCompteComptableEntity.getDesignation());
		  compteComptableValue.setDescription(pCompteComptableEntity.getDescription());
	    return compteComptableValue;
	  }

	  /** uniteArticleValue to uniteArticleEntite **/
	  public static CompteComptableEntity toEntity(CompteComptableValue pCompteComptableValue) {
		  CompteComptableEntity pCompteComptableEntity = new CompteComptableEntity();
	    if (pCompteComptableValue.getId() != null) {
	    	pCompteComptableEntity.setId(pCompteComptableValue.getId());
	    }
	    pCompteComptableEntity.setDesignation(pCompteComptableValue.getDesignation());
	    pCompteComptableEntity.setDescription(pCompteComptableValue.getDescription());
	    return pCompteComptableEntity;
	  }
	  
	  
	  
		/*** Compte Comptable PI  **/

	  /** unitelArticleEntite to uniteArticleValue **/
	  public static CompteComptablePIValue toValue(CompteComptablePIEntity pCompteComptableEntity) {
		  CompteComptablePIValue compteComptableValue = new CompteComptablePIValue();
		  compteComptableValue.setId(pCompteComptableEntity.getId());
		  compteComptableValue.setDesignation(pCompteComptableEntity.getDesignation());
		  compteComptableValue.setDescription(pCompteComptableEntity.getDescription());
	    return compteComptableValue;
	  }

	  /** uniteArticleValue to uniteArticleEntite **/
	  public static CompteComptablePIEntity toEntity(CompteComptablePIValue pCompteComptableValue) {
		  CompteComptablePIEntity pCompteComptableEntity = new CompteComptablePIEntity();
	    if (pCompteComptableValue.getId() != null) {
	    	pCompteComptableEntity.setId(pCompteComptableValue.getId());
	    }
	    pCompteComptableEntity.setDesignation(pCompteComptableValue.getDesignation());
	    pCompteComptableEntity.setDescription(pCompteComptableValue.getDescription());
	    return pCompteComptableEntity;
	  }
	  
	  
		/*** option Article  **/


	  public static OptionProduitValue toValue(OptionProduitEntity pOptionArticleEntity) {
		  OptionProduitValue optionArticleValue = new OptionProduitValue();
		  optionArticleValue.setId(pOptionArticleEntity.getId());
		  optionArticleValue.setDesignation(pOptionArticleEntity.getDesignation());
		  optionArticleValue.setDescription(pOptionArticleEntity.getDescription());
		  optionArticleValue.setTypesIds(pOptionArticleEntity.getTypesIds());
		  
		  optionArticleValue.setNature(pOptionArticleEntity.getNature());
		  
		  
	    return optionArticleValue;
	  }

	
	  public static OptionProduitEntity toEntity(OptionProduitValue pOptionArticleValue) {
		  OptionProduitEntity pOptionArticleEntity = new OptionProduitEntity();
	    if (pOptionArticleValue.getId() != null) {
	    	pOptionArticleEntity.setId(pOptionArticleValue.getId());
	    }
	    pOptionArticleEntity.setDesignation(pOptionArticleValue.getDesignation());
	    pOptionArticleEntity.setDescription(pOptionArticleValue.getDescription());
	    pOptionArticleEntity.setTypesIds(pOptionArticleValue.getTypesIds());
	    
	    pOptionArticleEntity.setNature(pOptionArticleValue.getNature());
	    
	    return pOptionArticleEntity;
	  }
	  
	  
	  
		/*** operation Produit  **/


	  public static OperationProduitValue toValue(OperationProduitEntity pOptionArticleEntity) {
		  OperationProduitValue optionArticleValue = new OperationProduitValue();
		  optionArticleValue.setId(pOptionArticleEntity.getId());
		  optionArticleValue.setDesignation(pOptionArticleEntity.getDesignation());
		  optionArticleValue.setDescription(pOptionArticleEntity.getDescription());
		  optionArticleValue.setTypesIds(pOptionArticleEntity.getTypesIds());
		  
		  optionArticleValue.setCout(pOptionArticleEntity.getCout());
		  optionArticleValue.setTemps(pOptionArticleEntity.getTemps());
		  optionArticleValue.setOrdre(pOptionArticleEntity.getOrdre());
		  
		  
	    return optionArticleValue;
	  }

	
	  public static OperationProduitEntity toEntity(OperationProduitValue pOptionArticleValue) {
		  OperationProduitEntity pOptionArticleEntity = new OperationProduitEntity();
	    if (pOptionArticleValue.getId() != null) {
	    	pOptionArticleEntity.setId(pOptionArticleValue.getId());
	    }
	    pOptionArticleEntity.setDesignation(pOptionArticleValue.getDesignation());
	    pOptionArticleEntity.setDescription(pOptionArticleValue.getDescription());
	    pOptionArticleEntity.setTypesIds(pOptionArticleValue.getTypesIds());
	    
	    
	    pOptionArticleEntity.setCout(pOptionArticleValue.getCout());
	    pOptionArticleEntity.setTemps(pOptionArticleValue.getTemps());
	    pOptionArticleEntity.setOrdre(pOptionArticleValue.getOrdre());
	    
	    
	    return pOptionArticleEntity;
	  }
	
	  
	  
	  /****************************Utils value to entite EbCouleur *********************************/
	  /** Converstion UtilsValue en UtilsEntite **/
	  public static UtilsEntite toEntity(UtilsValue utilsValue) {
		  UtilsEntite vEbUtilsEntite = new UtilsEntite();
	    if (utilsValue.getId() != null) {
	    	vEbUtilsEntite.setId(utilsValue.getId());
	    }
	    vEbUtilsEntite.setDesignation(utilsValue.getDesignation());
	    vEbUtilsEntite.setDescription(utilsValue.getDescription());
	    vEbUtilsEntite.setType(utilsValue.getType());
	    
	    
	    return vEbUtilsEntite;
	  }

	  /** Converstion UtilsEntite entite en UtilsValue **/
	  public static UtilsValue toValue(UtilsEntite vEbUtilsEntite) {
		  UtilsValue ebCouleurValue = new UtilsValue();
	    ebCouleurValue.setId(vEbUtilsEntite.getId());
	    
	    ebCouleurValue.setDesignation(vEbUtilsEntite.getDesignation());
	    ebCouleurValue.setDescription(vEbUtilsEntite.getDescription());
	    ebCouleurValue.setType(vEbUtilsEntite.getType());
	    
	    
	    return ebCouleurValue;
	  }
	  
	  //molds
	  
	  
		public static MoldsEntite toEntity(MoldsValue moldsValue) {
			MoldsEntite vEbMoldsEntite = new MoldsEntite();
			if (moldsValue.getId() != null) {
				vEbMoldsEntite.setId(moldsValue.getId());
			}
			vEbMoldsEntite.setDesignation(moldsValue.getDesignation());
			vEbMoldsEntite.setReference(moldsValue.getReference());
			vEbMoldsEntite.setEmplacement(moldsValue.getEmplacement());
			return vEbMoldsEntite;
		}

		/** Converstion MoldsEntite entite en MoldsValue **/
		public static MoldsValue toValue(MoldsEntite moldsEntite) {
			MoldsValue ebMoldsValue = new MoldsValue();
			ebMoldsValue.setId(moldsEntite.getId());
			ebMoldsValue.setDesignation(moldsEntite.getDesignation());
			ebMoldsValue.setReference(moldsEntite.getReference());
			ebMoldsValue.setEmplacement(moldsEntite.getEmplacement());
			return ebMoldsValue;
		}
		public static ArticleProduitValue toValueEnrichi(ArticleProduitEntity entity) 
		{
			ArticleProduitValue articleProduitValue=toValue(entity);
			if(entity.getId() != null)
			{
				articleProduitValue.setReferenceArticle(entity.getProduit().getReference());
				articleProduitValue.setDesignationArticle(entity.getProduit().getDesignation());

			
				
				
			    
			}
			return articleProduitValue;
			
				
			
		}
		

          }
