import React from "react";
import "./global.css";

export default function DashboardApp() {
    return (
        <div className="p-8">

            {/* Page Title */}
            <h2 className="text-3xl font-bold mb-6 text-healthcare-primaryDark">
                Dashboard Overview
            </h2>

            {/* Stats Section */}
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">

                <MetricCard
                    label="Total Patients"
                    value="152"
                    color="primary"
                />

                <MetricCard
                    label="Active Consultants"
                    value="38"
                    color="success"
                />

                <MetricCard
                    label="Appointments Today"
                    value="21"
                    color="positive"
                />

            </div>

            {/* Lower Section */}
            <div className="mt-10 grid grid-cols-1 lg:grid-cols-2 gap-6">

                {/* Recent Activity */}
                <div className="p-6 bg-white rounded-xl shadow border">
                    <h3 className="text-xl font-semibold text-healthcare-primaryDark mb-4">
                        Recent Activities
                    </h3>

                    <ul className="space-y-3 text-healthcare-text">
                        <li className="p-3 bg-healthcare-bg rounded-lg border">
                            🧑‍⚕️ Dr. Silva updated a patient report.
                        </li>
                        <li className="p-3 bg-healthcare-bg rounded-lg border">
                            👤 New patient registration completed.
                        </li>
                        <li className="p-3 bg-healthcare-bg rounded-lg border">
                            📅 Appointment booked for 3 PM.
                        </li>
                    </ul>
                </div>

                {/* System Health */}
                <div className="p-6 bg-white rounded-xl shadow border">
                    <h3 className="text-xl font-semibold text-healthcare-primaryDark mb-4">
                        System Health
                    </h3>

                    <div className="space-y-4">
                        <HealthStatus label="API Status" status="Operational" color="success" />
                        <HealthStatus label="Database" status="Online" color="positive" />
                        <HealthStatus label="Queue System" status="Stable" color="primary" />
                    </div>
                </div>
            </div>

        </div>
    );
}

/* -------------------------
   COMPONENT: Metrics Card
-------------------------- */

function MetricCard({
                        label,
                        value,
                        color,
                    }: {
    label: string;
    value: string;
    color: "primary" | "success" | "positive" | "danger";
}) {
    return (
        <div className="p-6 bg-white rounded-xl shadow border hover:shadow-lg transition">
            <h3 className="text-lg font-medium text-healthcare-muted">{label}</h3>
            <p className={`text-4xl font-bold mt-2 text-healthcare-${color}`}>
                {value}
            </p>
        </div>
    );
}

/* -------------------------
   COMPONENT: Health Status
-------------------------- */

function HealthStatus({
                          label,
                          status,
                          color,
                      }: {
    label: string;
    status: string;
    color: "primary" | "success" | "positive" | "danger";
}) {
    return (
        <div className="flex justify-between items-center p-3 bg-healthcare-bg rounded-lg border">
            <span className="text-healthcare-muted font-medium">{label}</span>
            <span className={`font-semibold text-healthcare-${color}`}>{status}</span>
        </div>
    );
}
