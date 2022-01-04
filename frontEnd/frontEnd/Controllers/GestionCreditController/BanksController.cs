using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Model.GestionCredit;
using frontEnd.Data;
using Service.GestionCreditService;

namespace frontEnd.Controllers.GestionCreditController
{
    public class BanksController : Controller
    {
        private Context db = new Context();
        BankService bankService = new BankService();

        // GET: Banks
        public ActionResult Index()
        {
            return View(bankService.GetAllBanks());
        }
        // GET: Banks/IndexByDemands/5
        public ActionResult IndexByDemands(int idDemande)
        {
          
            
            return View(bankService.GetBanksByDemande(idDemande));
        }

        // GET: Banks/Details/5
        public ActionResult Details(int id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Bank bank = bankService.GetBankById(id);
            if (bank == null)
            {
                return HttpNotFound();
            }
            return View(bank);
        }

        // GET: Banks/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Banks/Create
        // Afin de déjouer les attaques par survalidation, activez les propriétés spécifiques auxquelles vous voulez établir une liaison. Pour 
        // plus de détails, consultez https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "id,CreditPotentielle,marge,offre,teaux,approuvation,nom")] Bank bank)
        {
            if (ModelState.IsValid)
            {
                bankService.AjouterBank(bank);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(bank);
        }

        // GET: Banks/Edit/5
        public ActionResult Edit(int id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            
                Bank bank = bankService.GetBankById(id);
            
            if (bank == null)
            {
                return HttpNotFound();
            }
            return View(bank);
        }

        // POST: Banks/Edit/5
        // Afin de déjouer les attaques par survalidation, activez les propriétés spécifiques auxquelles vous voulez établir une liaison. Pour 
        // plus de détails, consultez https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(int id,[Bind(Include = "id,CreditPotentielle,marge,offre,teaux,approuvation,nom")] Bank bank)
        {
            if (ModelState.IsValid)
            {
                bankService.updateBank(id, bank);
                return RedirectToAction("Index");
            }
            return View(bank);
        }

        // GET: Banks/Delete/5
        public ActionResult Delete(int id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Bank bank = bankService.GetBankById(id);
            if (bank == null)
            {
                return HttpNotFound();
            }
            return View(bank);
        }

        // POST: Banks/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Bank bank = bankService.GetBankById(id);
            bankService.DeleteBank(id);
            return RedirectToAction("Index");
        }
        public ActionResult DetailsBank(int id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Bank bank = bankService.GetBankById(id);
            if (bank == null)
            {
                return HttpNotFound();
            }
            return View(bank);
        }
        public ActionResult CalculerMarge(int idDemande) 
        {

            bankService.calculerMarge(idDemande);
            return RedirectToAction("IndexByDemands",new { idDemande = idDemande });
        }
        public ActionResult calculerCreditPotentielle(int idDemande)
        {
            bankService.CalculerCreditPotentielle(idDemande);
            return RedirectToAction("IndexByDemands", new { idDemande = idDemande });
        }
        public ActionResult CalculerOffre(int idDemande)
        {
            bankService.calculerOffre(idDemande);
            return RedirectToAction("IndexByDemands", new { idDemande = idDemande });
        }
        public ActionResult ApprouverBank(int id)
        {
            Bank bnk = bankService.GetBankById(id);
            int route = bnk.demandecredit.id;
            bankService.ApprouverBank(id);
            return RedirectToAction("IndexByDemands",new { idDemande = route });
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
