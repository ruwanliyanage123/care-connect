import React, { Suspense } from "react";
import { Link, Route, Routes } from "react-router-dom";

const DashboardApp = React.lazy(() => import("care_connect_dashboard/DashboardApp"));



export default function App() {
    return (
        <div className="min-h-screen flex flex-col">

            <header className="bg-sky-700 text-white px-6 py-4 flex justify-between">
                <h1 className="text-xl font-bold">CareConnect Portal</h1>

                <nav className="space-x-4">
                    <Link to="/" className="hover:underline">Dashboard</Link>
                    <Link to="/patients" className="hover:underline">Patients</Link>
                    <Link to="/consultants" className="hover:underline">Consultants</Link>
                    <Link to="/appointments" className="hover:underline">Appointments</Link>
                    <Link to="/billing" className="hover:underline">Billing</Link>
                </nav>
            </header>

            <main className="p-6 flex-1">
                <Routes>
                    <Route
                        path="/"
                        element={
                            <Suspense fallback={<div>Loading dashboard...</div>}>
                                <DashboardApp />
                            </Suspense>
                        }
                    />

                    <Route path="/patients" element={<div>Patients MFE (coming soon)</div>} />
                    <Route path="/consultants" element={<div>Consultants MFE (coming soon)</div>} />
                    <Route path="/appointments" element={<div>Appointments MFE (coming soon)</div>} />
                    <Route path="/billing" element={<div>Billing MFE (coming soon)</div>} />
                </Routes>
            </main>
        </div>
    );
}
