import React, { Suspense } from "react";
import { Link, Route, Routes } from "react-router-dom";

console.log("🔵 Root App Loaded");

// Load remote MFE
const DashboardApp = React.lazy(() => import("care_connect_dashboard/DashboardApp"));

export default function App() {
    return (
        <div className="min-h-screen flex flex-col bg-gray-50">

            {/* Header */}
            <header className="bg-sky-700 text-white px-6 py-4 shadow flex justify-between items-center">
                <h1 className="text-2xl font-bold">CareConnect Portal</h1>

                <nav className="space-x-6 text-lg">
                    <Link to="/" className="hover:underline">Dashboard</Link>
                    <Link to="/patients" className="hover:underline">Patients</Link>
                    <Link to="/consultants" className="hover:underline">Consultants</Link>
                    <Link to="/appointments" className="hover:underline">Appointments</Link>
                    <Link to="/billing" className="hover:underline">Billing</Link>
                </nav>
            </header>

            {/* Main Body */}
            <main className="flex-1 p-6">

                <div className="mb-4 p-3 bg-yellow-100 border border-yellow-300 rounded">
                    <p className="text-yellow-800 font-semibold">
                        🟡 Root App Loaded — Waiting for Dashboard MFE (4101)
                    </p>
                </div>

                <Routes>
                    <Route
                        path="/"
                        element={
                            <Suspense
                                fallback={
                                    <div className="text-blue-600 text-xl font-semibold">
                                        🔄 Loading Dashboard Microfrontend...
                                    </div>
                                }
                            >
                                <div className="mb-4 p-3 bg-green-100 border border-green-300 rounded">
                                    <p className="text-green-800 font-semibold">
                                        🟢 Dashboard MFE Loaded Successfully!
                                    </p>
                                </div>

                                <DashboardApp />
                            </Suspense>
                        }
                    />

                    <Route path="/patients" element={<div className="text-xl">Patients MFE (coming soon)</div>} />
                    <Route path="/consultants" element={<div className="text-xl">Consultants MFE (coming soon)</div>} />
                    <Route path="/appointments" element={<div className="text-xl">Appointments MFE (coming soon)</div>} />
                    <Route path="/billing" element={<div className="text-xl">Billing MFE (coming soon)</div>} />
                </Routes>
            </main>

            {/* Footer */}
            <footer className="bg-gray-200 text-center py-2 text-sm text-gray-600">
                CareConnect © 2025 — Microfrontend Architecture Demo
            </footer>
        </div>
    );
}
