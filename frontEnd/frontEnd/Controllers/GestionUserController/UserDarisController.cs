using frontEnd.Data;
using Model.GestionUser;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web.Mvc;

namespace frontEnd.Controllers.GestionUserController
{
    public class UserDarisController : Controller
    {
        private Context db = new Context();

        // GET: UserDaris
        public ActionResult Index()
        {
            return View(db.UserDaris.ToList());
        }

        // GET: UserDaris/Details/5
        public ActionResult Details(long? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            UserDari userDari = db.UserDaris.Find(id);
            if (userDari == null)
            {
                return HttpNotFound();
            }
            return View(userDari);
        }

        // GET: UserDaris/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: UserDaris/Create
        // Afin de déjouer les attaques par survalidation, activez les propriétés spécifiques auxquelles vous voulez établir une liaison. Pour 
        // plus de détails, consultez https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "userId,FirstName,LastName,Email,Password,PhoneNumber,Activated,ImageUrl")] UserDari userDari)
        {
            if (ModelState.IsValid)
            {
                db.UserDaris.Add(userDari);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(userDari);
        }

        // GET: UserDaris/Edit/5
        public ActionResult Edit(long? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            UserDari userDari = db.UserDaris.Find(id);
            if (userDari == null)
            {
                return HttpNotFound();
            }
            return View(userDari);
        }

        // POST: UserDaris/Edit/5
        // Afin de déjouer les attaques par survalidation, activez les propriétés spécifiques auxquelles vous voulez établir une liaison. Pour 
        // plus de détails, consultez https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "userId,FirstName,LastName,Email,Password,PhoneNumber,Activated,ImageUrl")] UserDari userDari)
        {
            if (ModelState.IsValid)
            {
                db.Entry(userDari).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(userDari);
        }

        // GET: UserDaris/Delete/5
        public ActionResult Delete(long? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            UserDari userDari = db.UserDaris.Find(id);
            if (userDari == null)
            {
                return HttpNotFound();
            }
            return View(userDari);
        }

        // POST: UserDaris/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(long id)
        {
            UserDari userDari = db.UserDaris.Find(id);
            db.UserDaris.Remove(userDari);
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
