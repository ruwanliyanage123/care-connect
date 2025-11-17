import React, { Suspense } from "react";
import { Link, Route, Routes } from "react-router-dom";

const DashboardApp = React.lazy(() => import("care_connect_dashboard/DashboardApp"));

export default function App() {
    return (
        <div className="flex h-screen bg-gray-100">

            {/* Sidebar */}
            <aside className="w-64 bg-white shadow-lg border-r">
                <div className="p-6 border-b">
                    <h1 className="text-2xl font-bold text-sky-700">CareConnect</h1>
                    <p className="text-sm text-gray-500 mt-1">Healthcare Portal</p>
                </div>

                <nav className="px-4 py-6 space-y-2">
                    <Link
                        to="/"
                        className="block px-4 py-2 rounded-lg hover:bg-sky-100 text-gray-700 font-medium"
                    >
                        🏠 Dashboard
                    </Link>

                    <Link
                        to="/patients"
                        className="block px-4 py-2 rounded-lg hover:bg-sky-100 text-gray-700 font-medium"
                    >
                        👨‍⚕️ Patients
                    </Link>

                    <Link
                        to="/consultants"
                        className="block px-4 py-2 rounded-lg hover:bg-sky-100 text-gray-700 font-medium"
                    >
                        🩺 Consultants
                    </Link>

                    <Link
                        to="/appointments"
                        className="block px-4 py-2 rounded-lg hover:bg-sky-100 text-gray-700 font-medium"
                    >
                        📅 Appointments
                    </Link>

                    <Link
                        to="/billing"
                        className="block px-4 py-2 rounded-lg hover:bg-sky-100 text-gray-700 font-medium"
                    >
                        💳 Billing
                    </Link>
                </nav>
            </aside>

            {/* Main Content Area */}
            <div className="flex-1 flex flex-col">

                {/* Top Bar */}
                <header className="h-16 bg-white shadow px-6 flex justify-between items-center">
                    <h2 className="text-xl font-semibold text-gray-700">Welcome, Admin</h2>

                    <div className="flex items-center gap-4">
                        <button className="px-4 py-2 bg-sky-600 text-white rounded-lg hover:bg-sky-700">
                            + New Appointment
                        </button>

                        <div className="w-10 h-10 rounded-full bg-sky-300 flex items-center justify-center font-bold text-white">
                            RL
                        </div>
                    </div>
                </header>

                {/* Page Content */}
                <main className="p-6 overflow-auto">
                    <Routes>
                        <Route
                            path="/"
                            element={
                                <Suspense fallback={<div>Loading dashboard...</div>}>
                                    <DashboardApp />
                                </Suspense>
                            }
                        />

                        <Route path="/patients" element={<ComingSoon title="Patients" />} />
                        <Route path="/consultants" element={<ComingSoon title="Consultants" />} />
                        <Route path="/appointments" element={<ComingSoon title="Appointments" />} />
                        <Route path="/billing" element={<ComingSoon title="Billing" />} />
                    </Routes>
                </main>
            </div>
        </div>
    );
}

function ComingSoon({ title }: { title: string }) {
    return (
        <div className="text-center mt-20">
            <h2 className="text-3xl font-bold text-gray-700">{title} MFE Coming Soon</h2>
            <p className="text-gray-500 mt-3">
                This module will be implemented as a separate microfrontend.
            </p>
        </div>
    );
}
