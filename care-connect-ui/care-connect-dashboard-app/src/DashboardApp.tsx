import React from "react";
import "./global.css";

const DashboardApp: React.FC = () => {
    // Later: replace these with real data from /api/v1/reports/dashboard
    const stats = {
        totalPatients: 152,
        activeConsultants: 38,
        todayAppointments: 21,
        monthRevenue: "LKR 1.2M",
    };

    const upcoming = [
        {
            time: "09:30",
            patient: "Chanaka Perera",
            consultant: "Dr. S. Wijesinghe",
            type: "Follow-up",
            status: "Confirmed",
        },
        {
            time: "10:15",
            patient: "Nimali Fernando",
            consultant: "Dr. T. Raj",
            type: "New Consultation",
            status: "Pending",
        },
        {
            time: "11:00",
            patient: "Arun Kumar",
            consultant: "Dr. L. Tan",
            type: "Teleconsult",
            status: "Confirmed",
        },
    ];

    const revenue = [
        { label: "Today", value: "LKR 120,000" },
        { label: "This Week", value: "LKR 540,000" },
        { label: "This Month", value: "LKR 2,430,000" },
    ];

    return (
        <div className="space-y-6">
            {/* Top header */}
            <div className="flex flex-col gap-3 md:flex-row md:items-center md:justify-between">
                <div>
                    <h2 className="text-2xl font-semibold text-sky-900">
                        Clinical Operations Dashboard
                    </h2>
                    <p className="text-sm text-slate-500 mt-1">
                        Real-time overview of patient flow, appointments, and billing for CareConnect.
                    </p>
                </div>
                <div className="flex gap-2">
                    <button className="px-3 py-1.5 rounded-full border border-sky-200 text-sm text-sky-700 bg-sky-50 hover:bg-sky-100 transition">
                        Today
                    </button>
                    <button className="px-3 py-1.5 rounded-full border border-slate-200 text-sm text-slate-600 hover:bg-slate-50 transition">
                        This Week
                    </button>
                    <button className="px-3 py-1.5 rounded-full border border-slate-200 text-sm text-slate-600 hover:bg-slate-50 transition">
                        This Month
                    </button>
                </div>
            </div>

            {/* KPI cards */}
            <div className="grid gap-4 md:grid-cols-4">
                <div className="bg-white border border-slate-100 rounded-2xl shadow-sm p-4">
                    <p className="text-xs font-medium text-slate-500 uppercase tracking-wide">
                        Total Patients
                    </p>
                    <p className="mt-2 text-3xl font-semibold text-sky-900">
                        {stats.totalPatients}
                    </p>
                    <p className="mt-1 text-xs text-emerald-600">
                        +12 new registrations today
                    </p>
                </div>

                <div className="bg-white border border-slate-100 rounded-2xl shadow-sm p-4">
                    <p className="text-xs font-medium text-slate-500 uppercase tracking-wide">
                        Active Consultants
                    </p>
                    <p className="mt-2 text-3xl font-semibold text-sky-900">
                        {stats.activeConsultants}
                    </p>
                    <p className="mt-1 text-xs text-slate-500">
                        Across OPD, IPD & Telemedicine
                    </p>
                </div>

                <div className="bg-white border border-slate-100 rounded-2xl shadow-sm p-4">
                    <p className="text-xs font-medium text-slate-500 uppercase tracking-wide">
                        Appointments Today
                    </p>
                    <p className="mt-2 text-3xl font-semibold text-sky-900">
                        {stats.todayAppointments}
                    </p>
                    <p className="mt-1 text-xs text-amber-600">
                        3 patients waiting in queue
                    </p>
                </div>

                <div className="bg-white border border-emerald-100 rounded-2xl shadow-sm p-4 bg-gradient-to-br from-emerald-50 to-white">
                    <p className="text-xs font-medium text-emerald-700 uppercase tracking-wide">
                        Month-to-date Revenue
                    </p>
                    <p className="mt-2 text-2xl font-semibold text-emerald-900">
                        {stats.monthRevenue}
                    </p>
                    <p className="mt-1 text-xs text-emerald-700">
                        +18% vs. last month
                    </p>
                </div>
            </div>

            {/* Bottom grid: upcoming + revenue */}
            <div className="grid gap-4 lg:grid-cols-[2fr,1.4fr]">
                {/* Upcoming appointments */}
                <div className="bg-white border border-slate-100 rounded-2xl shadow-sm p-4">
                    <div className="flex items-center justify-between mb-3">
                        <h3 className="text-sm font-semibold text-slate-800">
                            Upcoming Appointments (Today)
                        </h3>
                        <button className="text-xs text-sky-700 hover:underline">
                            View all
                        </button>
                    </div>

                    <div className="divide-y divide-slate-100">
                        {upcoming.map((item, idx) => (
                            <div
                                key={idx}
                                className="py-2 flex items-center justify-between gap-4"
                            >
                                <div className="flex items-center gap-3">
                                    <div className="h-9 w-9 rounded-full bg-sky-50 flex items-center justify-center text-xs font-medium text-sky-700">
                                        {item.time}
                                    </div>
                                    <div>
                                        <p className="text-sm font-medium text-slate-800">
                                            {item.patient}
                                        </p>
                                        <p className="text-xs text-slate-500">
                                            {item.consultant} &middot; {item.type}
                                        </p>
                                    </div>
                                </div>

                                <span
                                    className={
                                        "inline-flex items-center px-2 py-1 text-xs rounded-full border " +
                                        (item.status === "Confirmed"
                                            ? "bg-emerald-50 text-emerald-700 border-emerald-200"
                                            : "bg-amber-50 text-amber-700 border-amber-200")
                                    }
                                >
                  {item.status}
                </span>
                            </div>
                        ))}
                    </div>
                </div>

                {/* Revenue summary */}
                <div className="bg-white border border-slate-100 rounded-2xl shadow-sm p-4">
                    <h3 className="text-sm font-semibold text-slate-800 mb-3">
                        Revenue Summary
                    </h3>
                    <div className="space-y-3">
                        {revenue.map((r) => (
                            <div
                                key={r.label}
                                className="flex items-center justify-between text-sm"
                            >
                                <span className="text-slate-500">{r.label}</span>
                                <span className="font-medium text-emerald-800">
                  {r.value}
                </span>
                            </div>
                        ))}
                    </div>

                    <div className="mt-4 rounded-xl bg-sky-50 border border-sky-100 p-3 text-xs text-sky-800">
                        Latest update pulled from{" "}
                        <span className="font-semibold">Report Service</span>. Data
                        refresh every 5 minutes in production.
                    </div>
                </div>
            </div>
        </div>
    );
};

export default DashboardApp;
