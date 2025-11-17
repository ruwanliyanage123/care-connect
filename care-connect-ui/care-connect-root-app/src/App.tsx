import React, { Suspense } from "react";
import { Link, Route, Routes } from "react-router-dom";

const DashboardApp = React.lazy(() => import("care_connect_dashboard/DashboardApp"));

export default function App() {
    return (
        <div className="flex h-screen bg-healthcare-bg">

            {/* Sidebar */}
            <aside className="w-64 bg-white shadow-lg border-r">
                <div className="p-6 border-b bg-healthcare-primary text-white rounded-br-2xl">
                    <h1 className="text-2xl font-bold">CareConnect</h1>
                    <p className="text-sm opacity-90 mt-1">Healthcare Portal</p>
                </div>

                <nav className="px-4 py-6 space-y-2">
                    <NavItem to="/" label="Dashboard" icon="🏠" />
                    <NavItem to="/patients" label="Patients" icon="👨‍⚕️" />
                    <NavItem to="/consultants" label="Consultants" icon="🩺" />
                    <NavItem to="/appointments" label="Appointments" icon="📅" />
                    <NavItem to="/billing" label="Billing" icon="💳" />
                </nav>
            </aside>

            {/* Main Content */}
            <div className="flex-1 flex flex-col">

                {/* Top Header */}
                <header className="h-16 bg-white shadow px-6 flex justify-between items-center border-b">
                    <h2 className="text-xl font-semibold text-healthcare-text">Welcome, Admin</h2>

                    <div className="flex items-center gap-4">
                        <button className="px-4 py-2 bg-healthcare-primary text-white rounded-lg hover:bg-healthcare-primaryDark">
                            + New Appointment
                        </button>

                        <div className="w-10 h-10 rounded-full bg-healthcare-success flex items-center justify-center font-bold text-white">
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

function NavItem({ to, label, icon }: { to: string; label: string; icon: string }) {
    return (
        <Link
            to={to}
            className="block px-4 py-2 rounded-lg hover:bg-healthcare-primary/10 text-healthcare-text font-medium flex items-center gap-2"
        >
            <span>{icon}</span> {label}
        </Link>
    );
}

function ComingSoon({ title }: { title: string }) {
    return (
        <div className="text-center mt-20">
            <h2 className="text-3xl font-bold text-healthcare-primaryDark">{title} Module Coming Soon</h2>
            <p className="text-healthcare-muted mt-3">
                This module will be built as a separate microfrontend.
            </p>
        </div>
    );
}
