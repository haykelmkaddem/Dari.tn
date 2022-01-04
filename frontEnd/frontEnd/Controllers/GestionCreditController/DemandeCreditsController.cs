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
    public class DemandeCreditsController : Controller
    {
        private Context db = new Context();
        DemandeCreditService demandeService = new DemandeCreditService();


        // GET: DemandeCredits
        public ActionResult Index()
        {
            return View(demandeService.GetAllDemandeCredits());
        }

        // GET: DemandeCredits/Details/5
        public ActionResult Details(int id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            DemandeCredit demandeCredit = demandeService.GetDemandeById(id);
            if (demandeCredit == null)
            {
                return HttpNotFound();
            }
            return View(demandeCredit);
        }

        // GET: DemandeCredits/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: DemandeCredits/Create
        // Afin de déjouer les attaques par survalidation, activez les propriétés spécifiques auxquelles vous voulez établir une liaison. Pour 
        // plus de détails, consultez https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "id,salaire,montantDemande,duree,age,profesion")] DemandeCredit demandeCredit)
        {
            if (ModelState.IsValid)
            {
                demandeService.AjouterDemande(demandeCredit);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(demandeCredit);
        }

        // GET: DemandeCredits/Edit/5
        public ActionResult Edit(int id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            DemandeCredit demandeCredit = demandeService.GetDemandeById(id);
            if (demandeCredit == null)
            {
                return HttpNotFound();
            }
            return View(demandeCredit);
        }

        // POST: DemandeCredits/Edit/5
        // Afin de déjouer les attaques par survalidation, activez les propriétés spécifiques auxquelles vous voulez établir une liaison. Pour 
        // plus de détails, consultez https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(int id,[Bind(Include = "id,salaire,montantDemande,duree,age,profesion")] DemandeCredit demandeCredit)
        {
            if (ModelState.IsValid)
            { 
            demandeService.UpdateDemande(id,demandeCredit);
                
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(demandeCredit);
        }

        // GET: DemandeCredits/Delete/5
        public ActionResult Delete(int id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            DemandeCredit demandeCredit = demandeService.GetDemandeById(id);
            if (demandeCredit == null)
            {
                return HttpNotFound();
            }
            return View(demandeCredit);
        }

        // POST: DemandeCredits/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            DemandeCredit demandeCredit = demandeService.GetDemandeById(id);
            demandeService.DeleteDemande(id);
            db.SaveChanges();
            return RedirectToAction("Index");
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
