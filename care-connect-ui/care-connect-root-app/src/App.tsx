import React, { Suspense } from "react";
import { Link, Route, Routes, useLocation } from "react-router-dom";

const DashboardApp = React.lazy(() => import("care_connect_dashboard/DashboardApp"));
const PatientsApp = React.lazy(() => import("care_connect_patients/PatientsApp"));
const ConsultantsApp = React.lazy(() => import("care_connect_consultants/ConsultantsApp"));
const AppointmentsApp = React.lazy(() => import("care_connect_appointments/AppointmentsApp"));
const PaymentsApp = React.lazy(() => import("care_connect_payments/PaymentsApp"));


export default function App() {
    return (
        <Layout>
            <Routes>
                <Route
                    path="/"
                    element={
                        <Suspense fallback={<Loading text="Loading Dashboard..." />}>
                            <DashboardApp />
                        </Suspense>
                    }
                />

                <Route
                    path="/patients"
                    element={
                        <Suspense fallback={<Loading text="Loading Patients..." />}>
                            <PatientsApp />
                        </Suspense>
                    }
                />

                <Route
                    path="/consultants"
                    element={
                        <Suspense fallback={<Loading text="Loading Consultants..." />}>
                            <ConsultantsApp />
                        </Suspense>
                    }
                />
                <Route
                    path="/appointments"
                    element={
                        <Suspense fallback={<Loading text="Loading Appointments..." />}>
                            <AppointmentsApp />
                        </Suspense>
                    }
                />
                <Route
                    path="/billing"
                    element={
                        <Suspense fallback={<Loading text="Loading Payments..." />}>
                            <PaymentsApp />
                        </Suspense>
                    }
                />
            </Routes>
        </Layout>
    );
}

/* -------------------------------------------------------------------------- */
/*                               Layout Wrapper                               */
/* -------------------------------------------------------------------------- */

function Layout({ children }: { children: React.ReactNode }) {
    return (
        <div className="flex h-screen bg-healthcare-bg">
            <Sidebar />
            <div className="flex-1 flex flex-col">
                <TopHeader />
                <main className="p-6 overflow-auto">{children}</main>
            </div>
        </div>
    );
}

/* -------------------------------------------------------------------------- */
/*                                 Sidebar                                    */
/* -------------------------------------------------------------------------- */

function Sidebar() {
    return (
        <aside className="w-64 bg-white shadow-lg border-r flex flex-col">
            <div className="p-6 border-b bg-healthcare-primary text-white rounded-br-2xl">
                <h1 className="text-2xl font-bold">CareConnect</h1>
                <p className="text-sm opacity-90 mt-1">Healthcare Portal</p>
            </div>

            <nav className="px-4 py-6 space-y-2 flex flex-col">
                <NavItem to="/" label="Dashboard" icon="🏠" />
                <NavItem to="/patients" label="Patients" icon="👨‍⚕️" />
                <NavItem to="/consultants" label="Consultants" icon="🩺" />
                <NavItem to="/appointments" label="Appointments" icon="📅" />
                <NavItem to="/billing" label="Billing" icon="💳" />
            </nav>
        </aside>
    );
}

/* Highlight Active Menu Item */

function NavItem({
                     to,
                     label,
                     icon,
                 }: {
    to: string;
    label: string;
    icon: string;
}) {
    const { pathname } = useLocation();
    const active = pathname === to;

    return (
        <Link
            to={to}
            className={`block px-4 py-2 rounded-lg font-medium flex items-center gap-2
            ${active ? "bg-healthcare-primary text-white" : "text-healthcare-text hover:bg-healthcare-primary/10"}
        `}
        >
            <span>{icon}</span> {label}
        </Link>
    );
}

/* -------------------------------------------------------------------------- */
/*                              Top Header Bar                                */
/* -------------------------------------------------------------------------- */

function TopHeader() {
    return (
        <header className="h-16 bg-white shadow px-6 flex justify-between items-center border-b">
            <h2 className="text-xl font-semibold text-healthcare-text">
                Welcome, Admin
            </h2>
            <div className="flex items-center gap-4">
                <div className="w-10 h-10 rounded-full bg-healthcare-success flex items-center justify-center font-bold text-white">
                    RL
                </div>
            </div>
        </header>
    );
}

/* -------------------------------------------------------------------------- */
/*                            Coming Soon Component                            */
/* -------------------------------------------------------------------------- */

function ComingSoon({ title }: { title: string }) {
    return (
        <div className="text-center mt-20">
            <h2 className="text-3xl font-bold text-healthcare-primaryDark">
                {title} Module Coming Soon
            </h2>
            <p className="text-healthcare-muted mt-3">
                This module will be built as a separate microfrontend.
            </p>
        </div>
    );
}

/* -------------------------------------------------------------------------- */
/*                                Loading                                    */
/* -------------------------------------------------------------------------- */

function Loading({ text }: { text: string }) {
    return (
        <div className="text-center text-healthcare-text text-lg p-6 animate-pulse">
            {text}
        </div>
    );
}
